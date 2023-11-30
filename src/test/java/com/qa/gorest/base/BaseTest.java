package com.qa.gorest.base;

import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;

public class BaseTest {
	
	//service URLS
	 public static final String GOREST_ENDPOINT="/public/v2/users";
	 public static final String REQRES_ENDPOINT="/api/users";
	 public static final String CIRCUIT_ENDPOINT="/api/f1";
	 public static final String AMADEUS_TOKEN_ENDPOINT="/api/f1";
	 public static final String AMADEUS_FIGHT_BOOKING_ENDPOINT="/v1/shopping/flight-destinations";
	 ///v1/shopping/flight-destinations
	
	
	
	
	protected ConfigurationManager config;
	protected Properties prop;
	protected RestClient restClient;
	protected String baseURI;
	
	@Parameters({"baseURI"})
	@BeforeTest
	public void setUp(String baseURI) {
		
		RestAssured.filters(new AllureRestAssured());
		config=new ConfigurationManager();
		prop=config.init_prop();
		this.baseURI=baseURI;
		//String baseURI=prop.getProperty("baseURI");
		//restClient=new RestClient(prop, baseURI);
		
	}

}
