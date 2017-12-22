/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.repo;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.model.ResourceInUseException;
import com.siemens.cloudfoundation.ccuserpreferencesservice.AppInit;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.repository.dynamodb.UserPreferencesRepository;
import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.DummyDataProviderUtility;

/**
 * @author thakur
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AppInit.class)
@WebAppConfiguration

public class UserPreferencesRepositoryTest {

	private DynamoDBMapper dynamoDBMapper;

	@Autowired
	private AmazonDynamoDB amazonDynamoDB;

	@Autowired
	private UserPreferencesRepository repository;

	@Before
	public void setup() throws Exception {
		dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

		try {
			CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(UserPreferences.class);
			tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
			amazonDynamoDB.createTable(tableRequest);
		} catch (ResourceInUseException e) {
			System.err.println("The table exists");
		}

	//	dynamoDBMapper.batchDelete((List<UserPreferences>) repository.findAll());

		// add a user record

		repository.save(DummyDataProviderUtility.getDummyUserPreferences());

	}

	/**
	 * this is to test that the repo is able to save an object , and while
	 * retrieving it returns the same object for hash key
	 * 
	 */
	@Test
	public void testFindOne() {

		assertThat(repository.findOne(DummyDataProviderUtility.getDummyUserPreferences().getUserid()))
				.isEqualTo(DummyDataProviderUtility.getDummyUserPreferences());

	}

}