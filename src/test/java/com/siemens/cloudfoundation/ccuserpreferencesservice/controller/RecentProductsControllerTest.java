/* @<COPYRIGHT>@
 ==================================================
 Copyright 2017.
 Siemens Product Lifecycle Management Software Inc.
 ==================================================
 @<COPYRIGHT>@ 
*/
package com.siemens.cloudfoundation.ccuserpreferencesservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.siemens.cloudfoundation.ccuserpreferencesservice.AppInit;
import com.siemens.cloudfoundation.ccuserpreferencesservice.dto.ProductInfoDTO;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.RecentProduct;
import com.siemens.cloudfoundation.ccuserpreferencesservice.entity.UserPreferences;
import com.siemens.cloudfoundation.ccuserpreferencesservice.service.RecentProductsService;
import com.siemens.cloudfoundation.ccuserpreferencesservice.service.UserPreferencesService;
import com.siemens.cloudfoundation.ccuserpreferencesservice.utility.TestAppConstants;;

/**
 * This class does unit testing for methods defined in UserPreferencesController
 */
@RunWith(SpringRunner.class )
@WebMvcTest(value = {RecentProductsController.class}, secure = false)
@SpringBootTest(classes = AppInit.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class RecentProductsControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserPreferencesService userpreferencesService;
	
	@MockBean
	private RecentProductsService recentProductsService;

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

	/**
	 * Test to get recent product for a userID Used mockito to create and configure
	 * mock objects
	 */
	@Test
	public void getRecentProductsByUserIdTest() throws Exception {

		ProductInfoDTO mockProductInfoDTO = new ProductInfoDTO(TestAppConstants.DUMMY_DESCRIPTION,
				TestAppConstants.DUMMY_THUMBNAIL_URL, TestAppConstants.DUMMY_PRODUCT_ID,
				TestAppConstants.DUMMY_PRODUCT_NAME, TestAppConstants.DUMMY_URL,
				dateFormat.format(Calendar.getInstance().getTime()),"");

		List<ProductInfoDTO> recentProductList = new ArrayList<>();
		recentProductList.add(mockProductInfoDTO);

		String URI = "/users/userid/recentProducts";

		Mockito.when(recentProductsService.getRecentProductsByUserId(Mockito.anyString()))
				.thenReturn(recentProductList);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();

		String expectedJson = this.mapToJson(recentProductList);
		String outputInJson = response.getContentAsString();

		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Test to add a recent product for a userID Used mockito to create and
	 * configure mock objects
	 */
	@Test
	public void addRecentProductForUserIdTest() throws Exception {

		RecentProduct recentProduct = new RecentProduct();
		recentProduct.setProductId("product_1");
		String timeStamp = dateFormat.format(Calendar.getInstance().getTime());
		recentProduct.setTimestamp(timeStamp);
		String inputInJson = this.mapToJson(recentProduct);

		List<RecentProduct> recentProductList = new ArrayList<>();
		recentProductList.add(recentProduct);

		UserPreferences mockUserPreference = new UserPreferences();
		mockUserPreference.setUserid("user_1");
		mockUserPreference.setRecentProducts(recentProductList);
		String expectedJson = this.mapToJson(mockUserPreference);

		Mockito.when(
				recentProductsService.addRecentProductForUserId(Mockito.anyString(), Mockito.any(RecentProduct.class)))
				.thenReturn(mockUserPreference);

		String URI = "/users/userid/recentProducts";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();		
		assertThat(outputInJson).isEqualTo(expectedJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/**
	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
	 */
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
}