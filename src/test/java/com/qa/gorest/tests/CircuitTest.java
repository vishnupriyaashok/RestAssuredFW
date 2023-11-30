package com.qa.gorest.tests;

import java.util.List;

import org.junit.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.ApiHttpStatus;
import com.qa.gorest.utilities.JsonPathValidator;

import freemarker.ext.beans.BeansWrapper.MethodAppearanceDecisionInput;
import io.restassured.response.Response;

public class CircuitTest extends BaseTest {
	@BeforeMethod
	public void circuitTestSetUp() {
		restClient=new RestClient(prop, baseURI);
	}


	@Test
	public void getCircuitTest() {
		Response circuiitResponse=restClient.get(CIRCUIT_ENDPOINT+"/2017/circuits.json", true,false);
		circuiitResponse .then()
		                   .assertThat().log().all()
		                    .statusCode(ApiHttpStatus.OK_200.getCode());

		JsonPathValidator jv=new JsonPathValidator();
		
		List<String> countryList=jv.readList(circuiitResponse, "$..Circuits..country");
		System.out.println(countryList);
		
		Assert.assertTrue(countryList.contains("China"));
		
	}

}
