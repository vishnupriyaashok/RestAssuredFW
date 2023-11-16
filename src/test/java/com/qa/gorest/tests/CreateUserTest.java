package com.qa.gorest.tests;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utilities.StringUtils;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

public class CreateUserTest {
RestClient restClient;
@Test
public void createUserTest() {
	//post
	 User user= new User("tom",StringUtils.getRandomEmailId(),"male","active");
	 restClient=new RestClient();
	
	 Integer userId=restClient.post("/public/v2/users", "json", user, true)
	             .then().log().all()
	              .assertThat()
	               .statusCode(201)
	               .extract().body().path("id");
	 System.out.println("User id : "+userId);
	 
	 //get
	 restClient.get("/public/v2/users"+userId, true)
	             .then().log().all()
	              .assertThat()
	               .statusCode(200)
	               .assertThat()
	               .body("id",equalTo(userId));
	              
}

	
}
