package com.qa.gorest.client;
import java.util.Map;

import com.qa.gorest.frameworkexception.ApiFrameWorkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient {
	
	private static final String BASE_URI="https://gorest.co.in";
	private static final String BEARER_TOKEN="1fc7eed13a610131a3adaa015ae833b97cc2be0f35a587c9cb6e710f41a91c7b";
	
	private static RequestSpecBuilder specBuilder;
	
	
	static{
		specBuilder= new RequestSpecBuilder();
	}

	
	private void addAuthorizationHeader() {
		specBuilder.addHeader("Authorization", "Bearer "+BEARER_TOKEN);
	}
	
	
	private void setRequestContenrType(String contentType) {
		switch (contentType.toLowerCase()) {
		case "json":
			specBuilder.setContentType(ContentType.JSON);
			break;
			
		case "xml":
			specBuilder.setContentType(ContentType.XML);
			break;	
			
		case "text":
			specBuilder.setContentType(ContentType.TEXT);
			break;
			
		case "multipart":
			specBuilder.setContentType(ContentType.MULTIPART);
			break;

		default:
			System.out.println("pls pass the right content type");
			throw new ApiFrameWorkException("INVALIDCONTENTTYPE");
		}
	}
	
	
	private RequestSpecification createRequestSpec() {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		return specBuilder.build();
	}
	
	
	private RequestSpecification createRequestSpec(Map<String,String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		specBuilder.addHeaders(headersMap);
		return specBuilder.build();
	}
	
	
	private RequestSpecification createRequestSpec(Map<String,String> headersMap,Map<String,String> queryParamsMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		if(headersMap!=null) {
		specBuilder.addHeaders(headersMap);
		}
		if(queryParamsMap!=null) {
		specBuilder.addQueryParams(queryParamsMap);
		}
		return specBuilder.build();
	}
	
//for post call util 

	private RequestSpecification createRequestSpec(Object requestBody,String contentType) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContenrType(contentType);
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody,String contentType,Map<String,String> headersMap) {
		specBuilder.setBaseUri(BASE_URI);
		addAuthorizationHeader();
		setRequestContenrType(contentType);
		if(headersMap!=null) {
			specBuilder.addHeaders(headersMap);
		}
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	//Http Methods Utils
	//--------------------
	
	//for get calls
	 public Response get(String serviceUrl,boolean log) {
		 if(log) {
			return  RestAssured.given(createRequestSpec()).log().all()
			 .when()
			  .get(serviceUrl);
		 }
		
		 return RestAssured.given(createRequestSpec())
		 .when()
		  .get(serviceUrl);
	 }
	
	
	 
	 public Response get(String serviceUrl,Map<String,String> headersMap,boolean log) {
		 if(log) {
			return  RestAssured.given(createRequestSpec(headersMap)).log().all()
			 .when()
			  .get(serviceUrl);
		 }
		
		 return RestAssured.given(createRequestSpec(headersMap))
		 .when()
		  .get(serviceUrl);
	 }
	 
	 
	 public Response get(String serviceUrl,Map<String,String> queryParamsMap,Map<String,String> headersMap,boolean log) {
		 if(log) {
			return  RestAssured.given(createRequestSpec(headersMap, queryParamsMap)).log().all()
			 .when()
			  .get(serviceUrl);
		 }
		
		 return RestAssured.given(createRequestSpec(headersMap, queryParamsMap))
		 .when()
		  .get(serviceUrl);
	 }
	 
	 
	 
//for post Calls 
	 
	 public Response post(String serviceUrl,String contentType,Object requestBody,boolean log) {
			if(log) {
				return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
				             .when()
				              .post(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(requestBody, contentType))
		             .when()
		              .post(serviceUrl);
		 }
		 
	 
	 public Response post(String serviceUrl,String contentType,Object requestBody,Map<String,String> headersMap,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
			             .when()
			              .post(serviceUrl);
		} 
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap))
	             .when()
	              .post(serviceUrl);
	 }
	 
	
	 // for put calls
	 
	 public Response put(String serviceUrl,String contentType,Object requestBody,boolean log) {
			if(log) {
				return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
				             .when()
				              .put(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(requestBody, contentType))
		             .when()
		              .put(serviceUrl);
		 }
		 
	 
	 public Response put(String serviceUrl,String contentType,Object requestBody,Map<String,String> headersMap,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
			             .when()
			              .put(serviceUrl);
		} 
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap))
	             .when()
	              .put(serviceUrl);
	 }
	 
	 
	 //fot patch calls
	 
	 
	 public Response patch(String serviceUrl,String contentType,Object requestBody,boolean log) {
			if(log) {
				return RestAssured.given(createRequestSpec(requestBody, contentType)).log().all()
				             .when()
				              .patch(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(requestBody, contentType))
		             .when()
		              .patch(serviceUrl);
		 }
		 
	 
	 public Response patch(String serviceUrl,String contentType,Object requestBody,Map<String,String> headersMap,boolean log) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap)).log().all()
			             .when()
			              .patch(serviceUrl);
		} 
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap))
	             .when()
	              .patch(serviceUrl);
	 }
	 
	 
	 //for delete calls
	 
	 
	 public Response delete(String serviceUrl,boolean log) {
			if(log) {
				return RestAssured.given(createRequestSpec()).log().all()
				             .when()
				              .delete(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec())
		             .when()
		              .delete(serviceUrl);
		 }
		 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
