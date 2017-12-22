/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.dto;

import java.util.List;

/**
 * @author thakur
 * 
 * dto for final data transfer on get request for recent products for a user id
 *
 */
public class UserPreferencesDTO {

	private String userId;
	private List<ProductInfoDTO> recentProducts;

	/**
	 * @return the userId
	 */
	public String getUserid() {
		return userId;
	}

	/**
	 * @param userid
	 *            the userId to set
	 */
	public void setUserid(String userid) {
		this.userId = userid;
	}

	/**
	 * @return the recentProducts
	 */
	public List<ProductInfoDTO> getRecentProducts() {
		return recentProducts;
	}

	/**
	 * @param recentProducts
	 *            the recentProducts to set
	 */
	public void setRecentProducts(List<ProductInfoDTO> recentProducts) {
		this.recentProducts = recentProducts;
	}

	@Override
	public String toString() {
		return "UserPreferencesDTO [userid=" + userId + ", recentProducts=" + recentProducts + "]";
	}

}
