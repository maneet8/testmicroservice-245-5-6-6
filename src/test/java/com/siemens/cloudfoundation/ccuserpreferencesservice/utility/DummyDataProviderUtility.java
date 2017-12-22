/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.utility;

import java.util.ArrayList;
import java.util.List;

import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;

/**
 * @author thakur
 *
 *	utility class to provide dummy data to test classes
 */
public class DummyDataProviderUtility {
	
	
	
	/**
	 * @return returns dummy UserPreferences object
	 */
	public static UserPreferences getDummyUserPreferences() {
		UserPreferences dummyUserPreferences = new UserPreferences();
		dummyUserPreferences.setUserid(TestAppConstants.DUMMY_EXISTING_USER);
		dummyUserPreferences.setRecentProducts(DummyDataProviderUtility.getDummyRecentProducts());
		return dummyUserPreferences;
	}

	/**
	 * @param productId product id to be set
	 * @return returns dummy RecentProduct object
	 */
	public static RecentProduct getDummyRecentProduct(String productId) {
		RecentProduct dummyProduct = new RecentProduct();
		dummyProduct.setProductId(productId);
		dummyProduct.setTimestamp(TestAppConstants.DUMMY_TIMESTAMP);
		return dummyProduct;
	}

	/**
	 * @return returns dummy Recent Product List
	 */
	public static List<RecentProduct> getDummyRecentProducts() {
		List<RecentProduct> dummyRecentProducts = new ArrayList<>();
		dummyRecentProducts.add(DummyDataProviderUtility.getDummyRecentProduct(TestAppConstants.DUMMY_PRODUCT_ID));
		return dummyRecentProducts;
	}
}
