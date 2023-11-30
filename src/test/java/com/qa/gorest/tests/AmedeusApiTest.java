package com.qa.gorest.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.ApiHttpStatus;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class AmedeusApiTest extends BaseTest{
	
	private String accessToken;
	
	
	@Parameters({"baseURI","grantType","clientId","clientSecret"})	
	@BeforeMethod
	public void flightApiSetUp(String baseURI,String grantType,String clientId,String clientSecret) {
		
		restClient=new RestClient(prop, baseURI);
		accessToken=restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT, grantType, clientId, clientSecret);
		System.out.println("inside the before method access token is "+accessToken);
	}
	
	
	@Test
	public void getFlightInfoTest() { 
		RestClient restClient=new RestClient(prop, baseURI);
		
		Map<String,Object> queryParamsMap=new HashMap<String,Object>();
		queryParamsMap.put("origin", "PAR");
		queryParamsMap.put("maxPrice", ApiHttpStatus.OK_200.getCode());
		
		Map<String,String> headersMap=new HashMap<String,String>();
		headersMap.put("Authorization", "Bearer "+accessToken);
		
		
	Response flightDataResponse=restClient.get(AMADEUS_FIGHT_BOOKING_ENDPOINT, queryParamsMap, headersMap, true, true)
		           .then().log().all()
		             .assertThat()
		              .statusCode(ApiHttpStatus.OK_200.getCode())
		               .and()
		                .extract()
		                 .response();
			
	JsonPath js=flightDataResponse.jsonPath();
	String type=js.get("data[0].type");
	System.out.println(type );//flight-destination


}}
