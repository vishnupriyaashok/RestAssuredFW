package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.ApiHttpStatus;

import io.qameta.allure.Description;

import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
public class GetUserTest extends BaseTest{
	
	@BeforeMethod
	public void getUserSetup() {
		restClient=new RestClient(prop, baseURI);
	}
	
	
	@Test(enabled=false,description = "this test is in progress")
	@Description("this test in progress")
	public void getAllUsersTest() {
		restClient.get(GOREST_ENDPOINT, true,true)
		 .then()
		  .assertThat().log().all()
		   .statusCode(ApiHttpStatus.OK_200.getCode());
		
	}

	
	  @Test public void getUserTest() { 
		  restClient.get(GOREST_ENDPOINT+"/5716397", true,true) 
		  .then()
		   .assertThat().log().all() 
		    .statusCode(ApiHttpStatus.OK_200.getCode()).and()
	          .body("id",equalTo(5716397)); }
	 
	
	@Test
	public void getUserWithQueryParamsTest() {
		Map<String, Object> queryParamsMap=new HashMap<String,Object>();
		queryParamsMap.put("name", "Govinda");
	    queryParamsMap.put("status", "inactive");
	
		restClient.get(GOREST_ENDPOINT, queryParamsMap,null,false,true)
	           .then().log().all()
		            .assertThat()
	                  .statusCode(ApiHttpStatus.OK_200.getCode());
		  
	
	
}
}