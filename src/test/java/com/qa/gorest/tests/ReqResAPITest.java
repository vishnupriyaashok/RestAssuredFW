package com.qa.gorest.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.ApiHttpStatus;

public class ReqResAPITest extends BaseTest{
	
	@BeforeMethod
	public void eeqResAPISetUp() {
		restClient=new RestClient(prop, baseURI);
	}

	@Test
	public void getReqResUsersTest() {
		restClient.get(REQRES_ENDPOINT+"/2", true,false)
		 .then()
		  .assertThat().log().all()
		   .statusCode(ApiHttpStatus.OK_200.getCode());
		
	}


}
