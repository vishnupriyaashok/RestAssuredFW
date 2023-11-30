package com.qa.gorest.utilities;

import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkexception.ApiFrameWorkException;

public class JsonPathValidator {
//refer JsonPathTest in restAssured core project
	
	private String getJsonResponseAsString(Response response) {
		return response.getBody().asString();
	}
	
	
	
	public <T> T read(Response response,String jsonPath) {
		String jsonResponse=getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonResponse,jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameWorkException(jsonPath+"is not found");	
		}	
	}
	
	
	public <T> List<T> readList(Response response,String jsonPath) {
		String jsonResponse=getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonResponse,jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameWorkException(jsonPath+"is not found");	
		}	
	}
	
	 
	public <T> List<Map<String,T>> readListOfMaps(Response response,String jsonPath) {
		String jsonResponse=getJsonResponseAsString(response);
		try {
		return JsonPath.read(jsonResponse,jsonPath);
		}
		catch(PathNotFoundException e) {
			e.printStackTrace();
			throw new ApiFrameWorkException(jsonPath+"is not found");	
		}	
	}
	
	
	
}
