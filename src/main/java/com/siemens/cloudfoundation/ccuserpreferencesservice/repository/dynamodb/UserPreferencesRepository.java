/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.repository.dynamodb;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;

/**
 * @author thakur 
 */

/**
 * Represents Dao layer for UserPreferences , a concrete class will be generated
 * by Spring data at runtime
 */

@Repository
@EnableScan
public interface UserPreferencesRepository extends CrudRepository<UserPreferences, String> {

}
