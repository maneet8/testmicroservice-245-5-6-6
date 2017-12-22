/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;
import com.siemens.cloudfoundation.ccuserpreferencesservice.repository.dynamodb.UserPreferencesRepository;

/**
 * @author thakur
 */

/**
 * Service layer for UserPreferences
 * 
 */

@Service
public class UserPreferencesService {

	@Value("${productServiceApiUrl:http://product-service.us-west-2.elasticbeanstalk.com/products/}")
	private String productServiceApiUrl;

	@Autowired
	private UserPreferencesRepository userPreferencesRepository;

	public UserPreferences getUserPreferences(String userid) throws UserIdDoesNotExistException {

		UserPreferences userPreference = userPreferencesRepository.findOne(userid);

		if (null == userPreference) {
			throw new UserIdDoesNotExistException(userid);
		}
		return userPreference;
	}
	public UserPreferences saveUserPreference(UserPreferences userPreference) {
		return userPreferencesRepository.save(userPreference);
	}
}
