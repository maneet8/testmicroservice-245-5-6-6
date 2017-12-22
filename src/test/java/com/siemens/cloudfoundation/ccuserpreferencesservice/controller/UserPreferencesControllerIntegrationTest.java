/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.controller;

import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.siemens.cloudfoundation.ccuserpreferencesservice.AppInit;
import com.siemens.cloudfoundation.ccuserpreferencesservice.dto.ProductInfoDTO;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.repository.dynamodb.UserPreferencesRepository;

/**
 * This class does the end to end testing for UserPreferencesController End to
 * end testing involves following cases - 1.Test successful insertion of a dummy
 * user with ID as "john.doe" and its recent product 2.Test successful fetching
 * dummy user's recent products
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserPreferencesControllerIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private UserPreferencesRepository userpreferencesrepository;

	private HttpHeaders headers = new HttpHeaders();
	private RecentProduct recentProduct = new RecentProduct();
	private String timeStamp;
	private List<RecentProduct> recentProductList;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * method to initialize a recent product with timestamp
	 */
	@Before
	public void setup() {

		recentProduct = new RecentProduct();
		recentProduct.setProductId("1001");
		timeStamp = dateFormat.format(Calendar.getInstance().getTime());
		recentProduct.setTimestamp(timeStamp);
		recentProductList = new ArrayList<>();
		recentProductList.add(recentProduct);
	}

	/**
	 * test to add a recent product for a userID
	 */
	@Test
	public void addRecentProductForUserIdTest() throws Exception {
		
		try {
		userpreferencesrepository.delete("john.doe");
		UserPreferences userPreference = new UserPreferences();
		userPreference.setUserid("john.doe");
		
		userpreferencesrepository.save(userPreference);
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		UserPreferences userPreference = new UserPreferences();
		userPreference.setUserid("john.doe");
		userPreference.setRecentProducts(recentProductList);

		String URIToAddRecentProductForUserId = "/users/john.doe/recentProducts";

		HttpEntity<RecentProduct> entity = new HttpEntity<RecentProduct>(recentProduct, headers);
		ResponseEntity<UserPreferences> response = restTemplate.exchange(
				formFullURLWithPort(URIToAddRecentProductForUserId), HttpMethod.PUT, entity, UserPreferences.class);
		System.err.println(response.getBody().toString() + " over here");

		UserPreferences responseUserPrefernces = response.getBody();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(userPreference, responseUserPrefernces);
	}

	/**
	 * test to retrieve recent products for a userID
	 */
	@Test
	public void getRecentProductsByUserIdTest() throws Exception {

		String URIToGetRecentProductForUserId = "/users/john.doe/recentProducts";
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<List<ProductInfoDTO>> response = restTemplate.exchange(
				formFullURLWithPort(URIToGetRecentProductForUserId), HttpMethod.GET, entity,
				new ParameterizedTypeReference<List<ProductInfoDTO>>() {
				});
		
		

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(recentProductList.get(0).getProductId(), response.getBody().get(0).getProductId());
		

	}

	/**
	 * This utility method to create the url for given uri. It appends the
	 * RANDOM_PORT generated port
	 */
	private String formFullURLWithPort(String uri) {

		String port = System.getenv("server.port") == null ? "4000" : System.getenv("server.port");		
		return "http://localhost:" + port + uri;
	}
}