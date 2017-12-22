/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.AdditionalLink;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;
import com.siemens.cloudfoundation.ccuserpreferencesservice.service.AdditionalLinksService;

/**
 * @author rm9wnk
 *
 *
 *
 */

/**
 * Controller class that exposes apis for additional Links in Navigation Panel
 *
 */
@RestControllerAdvice
@RestController
@RequestMapping("/users/")
public class AdditionalLinksController {

	@Autowired
	private AdditionalLinksService additionalLinksService;

	/**
	 * Exposes get api to get all additional Links against a given user id
	 * 
	 * @param userid
	 *            user id
	 * @return list of additional Links for given user id
	 * @throws UserIdDoesNotExistException
	 *             if user id not found in db
	 */

	@GetMapping("{userid}/additionallinks")
	public List<AdditionalLink> getAdditionalLinks(@PathVariable("userid") String userId)
			throws UserIdDoesNotExistException {
		return additionalLinksService.getAdditionalLinks(userId);
	}

	/**
	 * Exposes put api to update the additional Links list for a given user id
	 * 
	 * @param userid
	 *            user id
	 * @param additionalLinks
	 *           additional Link that is to be updated for the given user id
	 * @return user id mapped against list of additional Link
	 * @throws UserIdDoesNotExistException
	 */
	@PutMapping(value = "{userid}/additionallinks")
	public UserPreferences addAdditionalLinks(@PathVariable("userid") String userId,
			@Valid @RequestBody List<AdditionalLink> additionalLinks) throws UserIdDoesNotExistException {
		return additionalLinksService.addAdditionalLinks(userId, additionalLinks);
	}
}
