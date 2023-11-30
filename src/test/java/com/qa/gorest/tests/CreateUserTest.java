package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.ApiConstants;
import com.qa.gorest.constants.ApiHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utilities.ExcelUtils;
import com.qa.gorest.utilities.StringUtils;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CreateUserTest extends BaseTest{
	@BeforeMethod
	public void createUserSetup() {
		restClient=new RestClient(prop, baseURI);
	}

	@DataProvider
	public Object[][] getUserTestData() {
		return new Object[][] {
			{"Tom","male","active"},
			{"Tommy","male","active"},
			{"Tim","male","inactive"}
	};
	}
	

	@DataProvider
	public Object[][] getUserTestDataFromSheet() {
		return ExcelUtils.getTestData(ApiConstants.GO_REST_API_USER_SHEET);
	}
	
	
	
@Test(dataProvider ="getUserTestDataFromSheet" )
public void createUserTest(String name,String gender,String status) {
	//post
	 User user= new User(name,StringUtils.getRandomEmailId(),gender,status);
	 int userId=restClient.post(GOREST_ENDPOINT, "json", user, true,true)
	             .then().log().all()
	              .assertThat()
	               .statusCode(ApiHttpStatus.CREATED_201.getCode())
	               .extract().path("id");
	 
	 System.out.println("User id : "+userId);
	 
	 //get
	 RestClient restClientGet=new RestClient(prop, baseURI);
	 restClientGet.get(GOREST_ENDPOINT+"/"+userId, true,true)
	             .then().log().all()
	              .assertThat()
	               .statusCode(ApiHttpStatus.OK_200.getCode())
	               .assertThat()
	               .body("id",equalTo(userId));
	              
	 
}
//RestAssured.config = RestAssured.config().encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false));

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
