package com.qa.gorest.tests;

import org.testng.annotations.Test;

import com.qa.gorest.client.RestClient;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;
public class GetUserTest {
	
	RestClient restClient;
	
	@Test
	public void getAllUsersTest() {
		restClient=new RestClient();
		restClient.get("/public/v2/users", true)
		 .then()
		  .assertThat().log().all()
		   .statusCode(200);
	}

	
	@Test
	public void getUserTest() {
		restClient=new RestClient();
		restClient.get("/public/v2/users/5714922", true)
		 .then()
		  .assertThat().log().all()
		   .statusCode(200).and()
		   .body("id",equalTo(5714922));
	}
	
	@Test
	public void getUserWithQueryParamsTest() {
		restClient=new RestClient();
		Map<String,String> queryParamsMap=new HashMap<String,String>();
		queryParamsMap.put("name", "Gill");
		queryParamsMap.put("status", "active");
		
		restClient.get("/public/v2/users", queryParamsMap,null,false)
		           .then().log().all()
		            .assertThat()
		             .statusCode(200);
		  
	
	
}
}