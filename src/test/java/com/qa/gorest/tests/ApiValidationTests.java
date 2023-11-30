package com.qa.gorest.tests;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.constants.ApiHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utilities.StringUtils;

public class ApiValidationTests extends BaseTest{
	@Test
	public void createUserSchemaTest() {
		//post
		 User user= new User("Tom",StringUtils.getRandomEmailId(),"male","inactive");
		restClient.post(GOREST_ENDPOINT, "json", user, true,true)
		             .then().log().all()
		              .assertThat()
		               .statusCode(ApiHttpStatus.CREATED_201.getCode())
		                .body(matchesJsonSchemaInClasspath("createuserschema.json"));
		 
	}

}
