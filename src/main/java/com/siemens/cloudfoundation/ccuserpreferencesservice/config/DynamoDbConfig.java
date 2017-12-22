/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.siemens.cloudfoundation.ccuserpreferencesservice.repository.dynamodb.UserPreferencesRepository;

/**
 * @author thakur
 */

/**
 * Configuraiton class for dynamo db
 *
 */
@Configuration
@EnableDynamoDBRepositories(basePackageClasses = UserPreferencesRepository.class)
public class DynamoDbConfig {

	/**
	 * Creates AmazonDynamoDB object using AmazonDynamoDBClientBuilder
	 * 
	 * @return AmazonDynamoDB object for dynamo db service
	 */
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.defaultClient();
	}

}