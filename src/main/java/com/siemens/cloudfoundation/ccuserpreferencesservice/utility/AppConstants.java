/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.utility;

/**
 * @author thakur
 */

/**
 * Utility enum containing App Constants
 *
 */
public enum AppConstants {

	NO_DATA_FROM_PRODUCT_SERVICE("No data recieved from product service"), USER_ID_DOES_NOT_EXIST(
			"user id < %s >  does not exist in our records."), RECENT_PRODUCT_ADDED(
					"recent products updated for user id : %s with product id : %s."), RECENT_PRODUCT_NOT_MODIFIED(
							"issue in updating recent products with product id : %s and user id  : %s"), PRODUCT_ID_NOT_PROVIDED(
									"product id required");

	private String value;

	private AppConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}


}