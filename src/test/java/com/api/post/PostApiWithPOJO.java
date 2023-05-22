package com.api.post;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class PostApiWithPOJO {
	
	//POJO - plain old java object 
	
	@Test
	public void createUserWithPojoTest() {
		
		//Create object for POJO class
		User user = new User("Samhitha","Samhitha@abcde.com","female","active");
		
		//convert java POJO into JSON  - Called Serialization
		
		ObjectMapper mapper = new ObjectMapper();
		String userJson = null;
		try {
			userJson = mapper.writeValueAsString(user);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// post call
		RestAssured.baseURI = "https://gorest.co.in";
		
		int userId = given().log().all()
			.contentType(ContentType.JSON)
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
			.body(userJson)
		.when().log().all()
			.post("/public/v2/users")
		.then().log().all()
			.assertThat()
				.statusCode(201)
			.and()
			.extract().path("id");
		
		
		System.out.println("New user id: "+userId);
	}

}
