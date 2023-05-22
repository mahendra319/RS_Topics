package com.api.post;



import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.Test;


public class PostApiTest {
	
	@Test
	public void postApi_Token_Test() {
	
		//JSON as a string
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body("{\"username\" : \"admin\",\"password\" : \"password123\"}")
		.when().log().all()
			.post("/auth");
		
			String token = response.jsonPath().getString("token");
			
			System.out.println("Authorization token: "+token);
		
		
		
	}
	
	@Test
	public void postApi_Token_JsonFile_Test() {
		
		//Passing credentials as Json File
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		Response response = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File(".\\src\\test\\java\\com\\api\\datafiles\\credentials.json"))
		.when().log().all()
			.post("/auth");
		
		
		String token = response.jsonPath().getString("token");
		
		System.out.println("Authorization token: "+token);
	}
	
	@Test
	public void postApi_Token_JsonFile_Test1() {
		
		//Passing credentials as Json File - 
		//extract().path()
RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String token = given().log().all()
			.contentType(ContentType.JSON)
			.body(new File(".\\src\\test\\java\\com\\api\\datafiles\\credentials.json"))
		.when().log().all()
			.post("/auth")
		.then()
			.extract().path("token");
		
		
//		String token = response.jsonPath().getString("token");
		
		System.out.println("Authorization token: "+token);
		
		Assert.assertNotNull(token);
	}
	
	

}
