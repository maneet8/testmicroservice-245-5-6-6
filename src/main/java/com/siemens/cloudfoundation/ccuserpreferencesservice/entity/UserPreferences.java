/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.entity;

/**
 * @author thakur
 *
 */

import java.util.List;

import javax.validation.constraints.NotNull;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 *
 * Entity class to represent a UserPreferences table
 *
 */

@DynamoDBTable(tableName = "UserPreferences")
public class UserPreferences {

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@NotNull
	@DynamoDBHashKey(attributeName = "UserId")
	private String userId;

	@DynamoDBAttribute(attributeName = "RecentProducts")
	private List<RecentProduct> recentProducts;
	
	@DynamoDBAttribute(attributeName = "AdditionalLinks")
	private List<AdditionalLink> additionalLinks;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recentProducts == null) ? 0 : recentProducts.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		return result;
	}

	public List<AdditionalLink> getAdditionalLinks() {
		return additionalLinks;
	}

	public void setAdditionalLinks(List<AdditionalLink> additionalLinks) {
		this.additionalLinks = additionalLinks;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPreferences other = (UserPreferences) obj;
		if (recentProducts == null) {
			if (other.recentProducts != null)
				return false;
		} else if (!recentProducts.equals(other.recentProducts))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/**
	 * @return the recentProducts
	 */
	public List<RecentProduct> getRecentProducts() {
		return recentProducts;
	}

	/**
	 * @param recentProducts
	 *            the recentProducts to set
	 */
	public void setRecentProducts(List<RecentProduct> recentProducts) {
		this.recentProducts = recentProducts;
	}

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

	@Override
	public String toString() {
		return "UserPreferences [userId=" + userId + ", recentProducts=" + recentProducts + ", additionalLinks="
				+ additionalLinks + "]";
	}

}
