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

import com.siemens.cloudfoundation.ccuserpreferencesservice.dto.ProductInfoDTO;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;
import com.siemens.cloudfoundation.ccuserpreferencesservice.service.RecentProductsService;

/**
 * @author thakur
 *
 *
 *
 */

/**
 * Controller class that exposes user preferences service apis
 *
 */
@RestControllerAdvice
@RestController
@RequestMapping("/users/")
public class RecentProductsController {

	@Autowired
	private RecentProductsService recentProductsService;

	/**
	 * Exposes get api to get all recent products against a given user id
	 * 
	 * @param userid
	 *            user id
	 * @return list of recent products for given user id
	 * @throws UserIdDoesNotExistException
	 *             if user id not found in db
	 */

	@GetMapping("{userid}/recentProducts")
	public List<ProductInfoDTO> getRecentProductsByUserId(@PathVariable("userid") String userid)
			throws UserIdDoesNotExistException {
		return recentProductsService.getRecentProductsByUserId(userid);

	}

	/**
	 * Exposes put api to update the recent products list for a given user id
	 * 
	 * @param userid
	 *            user id
	 * @param recentProduct
	 *            recent product that is to be updated for the given user id
	 * @return user id mapped against list of recent products
	 * @throws UserIdDoesNotExistException 
	 */
	@PutMapping(value = "{userid}/recentProducts")
	public UserPreferences addRecentProductForUserId(@PathVariable("userid") String userid,
			@Valid @RequestBody RecentProduct recentProduct) throws UserIdDoesNotExistException {
		return recentProductsService.addRecentProductForUserId(userid, recentProduct);

	}

}
