package com.qa.gorest.client;
import java.util.Map;
import java.util.Properties;

import com.qa.gorest.constants.ApiHttpStatus;
import com.qa.gorest.frameworkexception.ApiFrameWorkException;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class RestClient {
//	
//	private static final String BASE_URI="https://gorest.co.in";
//	private static final String BEARER_TOKEN="1fc7eed13a610131a3adaa015ae833b97cc2be0f35a587c9cb6e710f41a91c7b";
	
	private static RequestSpecBuilder specBuilder;
	private Properties prop;
	private String baseURI;
	private boolean isAuthorizationHeaderAdded=false;
	
//	
//	static{
//		specBuilder= new RequestSpecBuilder();
//	}

	public RestClient(Properties prop,String baseURI) {
		specBuilder= new RequestSpecBuilder();
		this.prop=prop;
		this.baseURI=baseURI;
	}
	
	
	
	
	private void addAuthorizationHeader() {
	if(!isAuthorizationHeaderAdded) {
		specBuilder.addHeader("Authorization", "Bearer "+prop.getProperty("tokenId"));
		isAuthorizationHeaderAdded=true;
	}
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
	
	
	private RequestSpecification createRequestSpec(boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {addAuthorizationHeader();}
		return specBuilder.build();
	}
	
	
	private RequestSpecification createRequestSpec(Map<String,String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
		addAuthorizationHeader();
		}
		specBuilder.addHeaders(headersMap);
		return specBuilder.build();
	}
	
	
	private RequestSpecification createRequestSpec(Map<String,String> headersMap,Map<String,Object> queryParamsMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		if(headersMap!=null) {
		specBuilder.addHeaders(headersMap);
		}
		if(queryParamsMap!=null) {
		specBuilder.addQueryParams(queryParamsMap);
		}
		return specBuilder.build();
	}
	
//for post call util 

	private RequestSpecification createRequestSpec(Object requestBody,String contentType,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
		setRequestContenrType(contentType);
		if(requestBody!=null) {
			specBuilder.setBody(requestBody);
		}
		return specBuilder.build();
	}
	
	private RequestSpecification createRequestSpec(Object requestBody,String contentType,Map<String,String> headersMap,boolean includeAuth) {
		specBuilder.setBaseUri(baseURI);
		if(includeAuth) {
			addAuthorizationHeader();
			}
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
	 public Response get(String serviceUrl,boolean log,boolean includeAuth ) {
		 if(log) {
			return  RestAssured.given(createRequestSpec(includeAuth)).log().all()
			 .when()
			  .get(serviceUrl);
		 }
		
		 return RestAssured.given(createRequestSpec(includeAuth))
		 .when()
		  .get(serviceUrl);
	 }
	
	
	 
	 public Response get(String serviceUrl,Map<String,String> headersMap,boolean log,boolean includeAuth) {
		 if(log) {
			return  RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all()
			 .when()
			  .get(serviceUrl);
		 }
		
		 return RestAssured.given(createRequestSpec(headersMap,includeAuth))
		 .when()
		  .get(serviceUrl);
	 }
	 
	 
	 public Response get(String serviceUrl,Map<String,Object> queryParamsMap,Map<String,String> headersMap,boolean log,boolean includeAuth) {
		 if(log) {
			return  RestAssured.given(createRequestSpec(headersMap, queryParamsMap,includeAuth)).log().all()
			 .when()
			  .get(serviceUrl);
		 }
		
		 return RestAssured.given(createRequestSpec(headersMap, queryParamsMap,includeAuth))
		 .when()
		  .get(serviceUrl);
	 }
	 
	 
	 
//for post Calls 
	 
	 public Response post(String serviceUrl,String contentType,Object requestBody,boolean log,boolean includeAuth) {
			if(log) {
				return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
				             .when()
				              .post(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		             .when()
		              .post(serviceUrl);
		 }
		 
	 
	 public Response post(String serviceUrl,String contentType,Object requestBody,Map<String,String> headersMap,boolean log,boolean includeAuth) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
			             .when()
			              .post(serviceUrl);
		} 
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
	             .when()
	              .post(serviceUrl);
	 }
	 
	
	 // for put calls
	 
	 public Response put(String serviceUrl,String contentType,Object requestBody,boolean log,boolean includeAuth) {
			if(log) {
				return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
				             .when()
				              .put(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		             .when()
		              .put(serviceUrl);
		 }
		 
	 
	 public Response put(String serviceUrl,String contentType,Object requestBody,Map<String,String> headersMap,boolean log,boolean includeAuth) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
			             .when()
			              .put(serviceUrl);
		} 
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
	             .when()
	              .put(serviceUrl);
	 }
	 
	 
	 //fot patch calls
	 
	 
	 public Response patch(String serviceUrl,String contentType,Object requestBody,boolean log,boolean includeAuth) {
			if(log) {
				return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth)).log().all()
				             .when()
				              .patch(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(requestBody, contentType,includeAuth))
		             .when()
		              .patch(serviceUrl);
		 }
		 
	 
	 public Response patch(String serviceUrl,String contentType,Object requestBody,Map<String,String> headersMap,boolean log,boolean includeAuth) {
		if(log) {
			return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth)).log().all()
			             .when()
			              .patch(serviceUrl);
		} 
		return RestAssured.given(createRequestSpec(requestBody, contentType, headersMap,includeAuth))
	             .when()
	              .patch(serviceUrl);
	 }
	 
	 
	 //for delete calls
	 
	 
	 public Response delete(String serviceUrl,boolean log,boolean includeAuth) {
			if(log) {
				return RestAssured.given(createRequestSpec(includeAuth)).log().all()
				             .when()
				              .delete(serviceUrl);
			} 
			return RestAssured.given(createRequestSpec(includeAuth))
		             .when()
		              .delete(serviceUrl);
		 }
		 
	 
	 
//get Access Token	
	 public String getAccessToken(String serviceUrl,String grantType,String clientId,String clientSecret) {
		 //1.Post -to get the access token
		 RestAssured.baseURI="https://test.api.amadeus.com";
		 String accessToken=RestAssured.given().log().all()
	                   .contentType(ContentType.URLENC)//application/x-www-form-urlencoded
	                    .formParam("grant_Type", grantType)
	                    .formParam("client_Id", clientId)
	                    .formParam("client_Secret", clientSecret)
	                  .when()
	                    .post(serviceUrl)
	                  .then().log().all()
	                    .assertThat()
	                     .statusCode(ApiHttpStatus.OK_200.getCode())
	                     .extract().path("access_token");
		 
		 System.out.println("access_token is :"+accessToken);

return accessToken;	 
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
