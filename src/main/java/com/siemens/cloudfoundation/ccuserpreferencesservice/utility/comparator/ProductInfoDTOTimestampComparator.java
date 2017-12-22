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

import com.siemens.cloudfoundation.ccuserpreferencesservice.dto.ProductInfoDTO;

/**
 * @author thakur
 *
 */
/**
 *         Sort by order in which oldest record will be on top
 *
 */
@Component
public class ProductInfoDTOTimestampComparator implements Comparator<ProductInfoDTO> {

	@Value("${appConstantsTimestampFormat:yyyyMMddHHmmss}")
	public String timestampFormat;

	@Override
	public int compare(ProductInfoDTO compareWith, ProductInfoDTO compareTo) {

		LocalDateTime compareWithDate = LocalDateTime.parse(compareWith.getTimestamp(),
				DateTimeFormatter.ofPattern(timestampFormat));

		LocalDateTime compareToDate = LocalDateTime.parse(compareTo.getTimestamp(),
				DateTimeFormatter.ofPattern(timestampFormat));

		return compareToDate.compareTo(compareWithDate);

	}
}
