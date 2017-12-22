
/* @<COPYRIGHT>@
==================================================
Copyright 2017.
Siemens Product Lifecycle Management Software Inc.
==================================================
@<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.AdditionalLink;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;

/**
* @author rm9wnk
*/

/**
* Service layer for Adding additional Links in Navigation Panel
* 
*/

@Service
public class AdditionalLinksService {

	@Value("${appConstantsRecentProductsLimit:5}")
	private int additionalLinksLimit;
	
	@Autowired
	private UserPreferencesService userPreferencesService;
	private UserPreferences userPreference;

	/**
	 * Returns a list of additional links mapped to given user id
	 * 
	 * @param userid
	 *            user id
	 * @return list of additional links for given user id
	 * @throws UserIdDoesNotExistException
	 *             if user id not found in db
	 */
	public List<AdditionalLink> getAdditionalLinks(String userId) throws UserIdDoesNotExistException {
		userPreference = userPreferencesService.getUserPreferences(userId);

		if (null == userPreference) {
			throw new UserIdDoesNotExistException(userId);
		}
		return userPreference.getAdditionalLinks();
	}
	
	/**
	 * Maps a new additional link entry for given user id
	 * 
	 * @param userid
	 *            user id
	 * @param additionalLink
	 *            additionalLink that is to be updated for the given user id
	 * @return user id mapped against list of additionalLinks
	 */
	
	public UserPreferences addAdditionalLinks(String userId, List<AdditionalLink> additionalLinks) throws UserIdDoesNotExistException {
		userPreference = userPreferencesService.getUserPreferences(userId);
		
		if (null == userPreference) {
			throw new UserIdDoesNotExistException(userId);
		}
		
		else {
			if(null==userPreference.getAdditionalLinks())
			{
				userPreference.setAdditionalLinks(additionalLinks);
			}else {
				
					for(AdditionalLink link:  additionalLinks)
					{
						if(!userPreference.getAdditionalLinks().contains(link))
							userPreference.getAdditionalLinks().add(link);		
						
					}
				
			}
			return userPreferencesService.saveUserPreference(userPreference);
		}
		
	}
}
