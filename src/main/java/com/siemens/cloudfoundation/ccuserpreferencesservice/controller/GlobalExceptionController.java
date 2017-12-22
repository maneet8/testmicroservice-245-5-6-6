/**
 * 
 */
package com.siemens.cloudfoundation.ccuserpreferencesservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.siemens.cloudfoundation.ccuserpreferencesservice.exception.UserIdDoesNotExistException;

/**
 * @author thakur
 */

/**
 * Global Exception handler
 */

@RestControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(value = { UserIdDoesNotExistException.class })
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public String handleBaseException(UserIdDoesNotExistException e) {

		e.printStackTrace();

		// call log service here

		return e.getMessage();
	}

}
