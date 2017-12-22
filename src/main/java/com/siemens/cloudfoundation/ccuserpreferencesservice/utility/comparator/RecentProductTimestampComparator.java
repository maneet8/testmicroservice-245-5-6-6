/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.utility.comparator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;

/**
 * @author thakur
 */

/**
 *         Sort by order in which oldest record will be on top
 *
 */
@Component
public class RecentProductTimestampComparator implements Comparator<RecentProduct> {

	@Value("${appConstantsTimestampFormat:yyyyMMddHHmmss}")
	public String timestampFormat;

	@Override
	public int compare(RecentProduct compareWith, RecentProduct compareTo) {

		LocalDateTime compareWithDate = LocalDateTime.parse(compareWith.getTimestamp(),
				DateTimeFormatter.ofPattern(timestampFormat));

		LocalDateTime compareToDate = LocalDateTime.parse(compareTo.getTimestamp(),
				DateTimeFormatter.ofPattern(timestampFormat));

		return compareWithDate.compareTo(compareToDate);

	}

}
