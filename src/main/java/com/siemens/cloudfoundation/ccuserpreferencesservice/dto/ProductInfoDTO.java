/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/

package com.siemens.cloudfoundation.ccuserpreferencesservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author thakur
 *
 *         dto to represent json response from product service
 *
 */
public class ProductInfoDTO {

	/**
	 * @param description
	 *            description of the product
	 * @param thumbnailUrl
	 *            url for the thumbnail to be displayed
	 * @param productId
	 *            id of the product
	 * @param productName
	 *            name of the product
	 * @param url
	 *            url to be linked when product thumbnail will be clicked
	 * @param timestamp
	 *            latest timestamp when user accessed this product
	 */
	public ProductInfoDTO(String description, String thumbnailUrl, String productId, String productName, String url,
			String timestamp, String smallThumbnail) {
		super();
		this.description = description;
		this.thumbnailUrl = thumbnailUrl;
		this.productId = productId;
		this.productName = productName;
		this.url = url;
		this.timestamp = timestamp;
		this.smallThumbnail = smallThumbnail;
	}

	public ProductInfoDTO() {

	}

	@JsonProperty("Description")
	private String description;
	@JsonProperty("thumbnailUrl")
	private String thumbnailUrl;
	@JsonProperty("product_id")
	private String productId;
	@JsonProperty("PRODUCT_NAME")
	private String productName;
	@JsonProperty("URL")
	private String url;
	@JsonProperty("smallThumbnail")
	private String smallThumbnail;

	public String getSmallThumbnail() {
		return smallThumbnail;
	}

	public void setSmallThumbnail(String smallThumbnail) {
		this.smallThumbnail = smallThumbnail;
	}

	private String timestamp;

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the thumbnailUrl
	 */
	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	/**
	 * @param thumbnailUrl
	 *            the thumbnailUrl to set
	 */
	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId
	 *            the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @param productName
	 *            the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductInfoDTO [description=" + description + ", thumbnailUrl=" + thumbnailUrl + ", productId="
				+ productId + ", productName=" + productName + ", url=" + url + ", timestamp=" + timestamp + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + ((thumbnailUrl == null) ? 0 : thumbnailUrl.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
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
		ProductInfoDTO other = (ProductInfoDTO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (thumbnailUrl == null) {
			if (other.thumbnailUrl != null)
				return false;
		} else if (!thumbnailUrl.equals(other.thumbnailUrl))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

}
