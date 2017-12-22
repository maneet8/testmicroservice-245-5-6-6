/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.exception;

import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.AppConstants;

/**
 * @author thakur
 */

/**
 * Custom Exception if a provided userid does not exist in database.
 *
 */
public class UserIdDoesNotExistException extends Exception {

	private static final long serialVersionUID = 4627657269119742429L;

	public UserIdDoesNotExistException(String userId) {
		super(String.format(AppConstants.USER_ID_DOES_NOT_EXIST.getValue(), userId));
	}

}
