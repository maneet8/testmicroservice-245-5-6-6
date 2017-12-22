/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

/**
 * @author thakur
 */

/**
 * Spring boot Initialization class
 */
@SpringBootApplication
@Configuration
public class AppInit {

	/**
	 * Spring entry point
	 * 
	 * @param args
	 *            arguments to be passed to spring container
	 */
	public static void main(String[] args) {

		SpringApplication.run(AppInit.class, args);
	}

}
