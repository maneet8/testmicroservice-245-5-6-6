/* @<COPYRIGHT>@
==================================================
Copyright 2017.
Siemens Product Lifecycle Management Software Inc.
==================================================
@<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.entity;

import javax.validation.constraints.NotNull;

/**
* @author thakur
*
*/

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
*
* Entity class to represent an additional Link
*
*/

@DynamoDBDocument
public class AdditionalLink {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((displayValue == null) ? 0 : displayValue.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdditionalLink other = (AdditionalLink) obj;
		if (displayValue == null) {
			if (other.displayValue != null)
				return false;
		} else if (!displayValue.equals(other.displayValue))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@NotNull
	@DynamoDBAttribute(attributeName = "DisplayValue")
	@JsonProperty("DisplayValue")
	private String displayValue;

	@DynamoDBAttribute(attributeName = "URL")
	@JsonProperty("URL")
	private String url;

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "AdditionalLink [displayValue=" + displayValue + ", url=" + url + "]";
	}
}