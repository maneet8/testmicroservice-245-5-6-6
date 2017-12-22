/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.siemens.cloudfoundation.ccuserpreferencesservice.AppInit;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;
import com.siemens.cloudfoundation.ccuserpreferencesservice.repository.dynamodb.UserPreferencesRepository;
import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.TestAppConstants;

/**
 * test cases for service layer
 * 
 * @author thakur
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInit.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class UserPreferencesServiceTest {
	
	@Autowired
	private RecentProductsService recentProductsService;

	@MockBean
	private UserPreferencesRepository userPreferencesRepository;

	@Before
	public void setUp() {

		Mockito.when(userPreferencesRepository.findOne(TestAppConstants.DUMMY_EXISTING_USER))
				.thenReturn(this.getDummyUserPreferences());

		Mockito.when(userPreferencesRepository.findOne(TestAppConstants.DUMMY_NON_EXISTING_USER))
				.thenReturn(null);

		Mockito.when(userPreferencesRepository.save(Matchers.any(UserPreferences.class)))
				.thenReturn(this.getDummyUserPreferences());
	}

	private UserPreferences getDummyUserPreferences() {
		UserPreferences dummyUserPreferences = new UserPreferences();
		dummyUserPreferences.setUserid(TestAppConstants.DUMMY_EXISTING_USER);
		dummyUserPreferences.setRecentProducts(this.getDummyRecentProducts());
		return dummyUserPreferences;
	}

	private RecentProduct getDummyRecentProduct(String productId) {
		RecentProduct dummyProduct = new RecentProduct();
		dummyProduct.setProductId(productId);
		dummyProduct.setTimestamp(TestAppConstants.DUMMY_TIMESTAMP);
		return dummyProduct;
	}

	private List<RecentProduct> getDummyRecentProducts() {
		List<RecentProduct> dummyRecentProducts = new ArrayList<>();
		dummyRecentProducts.add(this.getDummyRecentProduct(TestAppConstants.DUMMY_PRODUCT_ID));
		return dummyRecentProducts;
	}



	/**
	 * to test scenario when user does not exist in the db , so it should throw an
	 * exception
	 * 
	 * @throws UserIdDoesNotExistException
	 *             if user does not exist
	 */
	@Test(expected = UserIdDoesNotExistException.class)
	public void whenNonExistingUserProvidedShouldGiveExceptionTest() throws UserIdDoesNotExistException {

		recentProductsService.getRecentProductsByUserId(TestAppConstants.DUMMY_NON_EXISTING_USER);
	}

	/**
	 * to test scenario for new user , should return UserPreferences object with
	 * user id same as provided and list of Recent Product containing only one
	 * provided RecentProduct
	 * @throws UserIdDoesNotExistException 
	 */
	@Test(expected =UserIdDoesNotExistException.class)
	public void whenAddingRecentProductForNewUserTest() throws UserIdDoesNotExistException {

		UserPreferences foundUserPreferences = recentProductsService.addRecentProductForUserId(
				TestAppConstants.DUMMY_NON_EXISTING_USER,
				this.getDummyRecentProduct(TestAppConstants.DUMMY_PRODUCT_ID));

		assertThat(foundUserPreferences).isEqualTo(this.getDummyUserPreferences());
	}

	/**
	 * to test scenario for existing user , should return UserPreferences object
	 * with user id same as provided and recent product list with an extra new
	 * product provided in the request
	 * @throws UserIdDoesNotExistException 
	 */
	@Test
	public void whenAddingNewRecentProductForExistingUserTest() throws UserIdDoesNotExistException {

		UserPreferences testUserPreferences = this.getDummyUserPreferences();
		testUserPreferences.getRecentProducts()
				.add(this.getDummyRecentProduct(TestAppConstants.DUMMY_PRODUCT_ID2));

		Mockito.when(userPreferencesRepository.save(Matchers.any(UserPreferences.class)))
				.thenReturn(testUserPreferences);

		UserPreferences foundUserPreferences = recentProductsService.addRecentProductForUserId(
				TestAppConstants.DUMMY_EXISTING_USER,
				this.getDummyRecentProduct(TestAppConstants.DUMMY_PRODUCT_ID2));

		assertThat(foundUserPreferences).isEqualTo(testUserPreferences);
	}

}
