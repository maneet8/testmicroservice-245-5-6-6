/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.siemens.cloudfoundation.ccuserpreferencesservice.dto.ProductInfoDTO;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;
import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.AppConstants;
import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.comparator.ProductInfoDTOTimestampComparator;
import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.comparator.RecentProductTimestampComparator;

/**
 * @author thakur
 */

/**
 * Service layer for UserPreferences
 * 
 */

@Service
public class RecentProductsService {

	@Value("${appConstantsRecentProductsLimit:20}")
	private int recentProductsLimit;

	@Value("${appConstantsTimestampFormat:yyyyMMddHHmmss}")
	public String timestampFormat;

	@Value("${productServiceApiUrl:http://product-service.us-west-2.elasticbeanstalk.com/products/}")
	private String productServiceApiUrl;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private UserPreferencesService userPreferencesService;

	@Autowired
	private RecentProductTimestampComparator recentProductsTimestampComparator;

	@Autowired
	private ProductInfoDTOTimestampComparator productInfoDTOTimestampComparator;

	/**
	 * Returns a list of recent products mapped to given user id
	 * 
	 * @param userid
	 *            user id
	 * @return list of recent products for given user id
	 * @throws UserIdDoesNotExistException
	 *             if user id not found in db
	 */
	public List<ProductInfoDTO> getRecentProductsByUserId(String userid) throws UserIdDoesNotExistException {
		return this.getRecentProductsWithDetails(this.getUserPreferences(userid).getRecentProducts());
	}

	/**
	 * @param recentProducts
	 * @return
	 */
	private List<ProductInfoDTO> getRecentProductsWithDetails(List<RecentProduct> recentProducts) {

		ResponseEntity<List<ProductInfoDTO>> responseEntity = restTemplate.exchange(productServiceApiUrl,
				HttpMethod.GET, null, new ParameterizedTypeReference<List<ProductInfoDTO>>() {
				});

		if (null == responseEntity.getBody() || responseEntity.getBody().isEmpty())
			// since this will be replaced by nds service so didnt define any custom
			// exception for now!
			throw new RuntimeException(AppConstants.NO_DATA_FROM_PRODUCT_SERVICE.getValue());

		

		
		// optimizing
		Map<String, String> recentProductsMap = recentProducts.stream()
				.collect(Collectors.toMap(RecentProduct::getProductId, RecentProduct::getTimestamp));

		List<ProductInfoDTO> recentProductsWithDetails = responseEntity.getBody().stream()
				.filter(productInfo -> null != recentProductsMap.get(productInfo.getProductId())).map(productInfo -> {
					productInfo.setTimestamp(recentProductsMap.get(productInfo.getProductId()));
					return productInfo;
				}).collect(Collectors.toList());
		recentProductsWithDetails.sort(productInfoDTOTimestampComparator);

		return recentProductsWithDetails;
	}

	/**
	 * Maps a new recent product entry for given user id
	 * 
	 * @param userid
	 *            user id
	 * @param recentProduct
	 *            recent product that is to be updated for the given user id
	 * @return user id mapped against list of recent products
	 * @throws UserIdDoesNotExistException 
	 */
	public UserPreferences addRecentProductForUserId(String userid, RecentProduct recentProduct) throws UserIdDoesNotExistException {

		UserPreferences userPreference = userPreferencesService.getUserPreferences(userid);

		if (null == userPreference)
			return this.addIfEmpty(userid, recentProduct);
		else
			return this.updateUserPreferences(userPreference, recentProduct);
	}

	private UserPreferences updateUserPreferences(UserPreferences userPreference, RecentProduct recentProduct) {
		recentProduct.setTimestamp(DateTimeFormatter.ofPattern(timestampFormat).format(LocalDateTime.now()));

		if (userPreference.getRecentProducts().contains(recentProduct))
			return this.updateForALreadyExistingRecord(userPreference, recentProduct);

		if (userPreference.getRecentProducts().size() == recentProductsLimit)
			return this.updateForMaxRecentProductsLimit(userPreference, recentProduct);

		userPreference.getRecentProducts().add(recentProduct);
		return userPreferencesService.saveUserPreference(userPreference);
	}

	private UserPreferences updateForALreadyExistingRecord(UserPreferences userPreference,
			RecentProduct recentProduct) {
		userPreference.getRecentProducts().forEach(product -> {
			if (recentProduct.equals(product)) {
				product.setTimestamp(DateTimeFormatter.ofPattern(timestampFormat).format(LocalDateTime.now()));
			}
		});

		return userPreferencesService.saveUserPreference(userPreference);
	}

	private UserPreferences updateForMaxRecentProductsLimit(UserPreferences userPreference,
			RecentProduct recentProduct) {
		userPreference.getRecentProducts().sort(recentProductsTimestampComparator);
		userPreference.getRecentProducts().remove(0);

		userPreference.getRecentProducts().add(recentProduct);
		return userPreferencesService.saveUserPreference(userPreference);
	}

	private UserPreferences addIfEmpty(String userid, RecentProduct recentProduct) {

		List<RecentProduct> recentProducts = new ArrayList<>();

		recentProduct.setTimestamp(DateTimeFormatter.ofPattern(timestampFormat).format(LocalDateTime.now()));
		recentProducts.add(recentProduct);

		UserPreferences userPreference = new UserPreferences();
		userPreference.setUserid(userid);
		userPreference.setRecentProducts(recentProducts);

		return userPreferencesService.saveUserPreference(userPreference);

	}

	public UserPreferences getUserPreferences(String userid) throws UserIdDoesNotExistException {

		UserPreferences userPreference = userPreferencesService.getUserPreferences(userid);

		if (null == userPreference) {
			throw new UserIdDoesNotExistException(userid);
		}

		return userPreference;

	}
	
	public UserPreferences saveUserPreference(UserPreferences userPreference) {
		return userPreferencesService.saveUserPreference(userPreference);
	}

}
