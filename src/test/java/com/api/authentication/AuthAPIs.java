package com.api.authentication;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthAPIs {
	
	
	//Basic Auth : user name and password
	
	@Test
	public void basic_Auth_Api_Test() {
		
		String pathParam = "basic_auth";
		
		given().log().all()
			.pathParam("auth",pathParam )
			.auth()
				.preemptive()
					.basic("admin", "admin")
			
		.when().log().all()
			.get("https://the-internet.herokuapp.com/{auth}")
		.then().log().all()
			.assertThat()
				.statusCode(200);
	}
	
	
	
	@Test
	public void oAuth2_Api_test() {
		
		given().log().all()
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.get("https://gorest.co.in/public-api/users/?name=verma")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.header("Server", "cloudflare");
			
	}
	
	
	@Test
	public void oAuth2_Api_TokenAsHeader_Test() {
		
		given().log().all()
			.header("Authorization", "Bearer 7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.get("https://gorest.co.in/public-api/users/?name=verma")	
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.header("Server", "cloudflare");
			
	}
	
	
	
	@Test
	public void oAuth2_Api_withQueryParam_Test() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		given().log().all()
			.queryParam("name", "verma")
			.queryParam("status", "active")
			.auth()
				.oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49")
		.when().log().all()
			.get("/public-api/users/")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.header("Server", "cloudflare");
	}
	
	
	//Generate Access token at runtime using token api
	//Get string access token from response
	//Hit the next api using above generated token
	
	@Test
	public void oAuth2_GenerateAccessToken_Test() {
		
		
		RequestSpecification request = RestAssured.given()
						.formParam("client_id", "RestAssuredApiPractice")
						.formParam("client_secret", "")
						.formParam("granttype", "");
		
		
		Response response = request.post("https://coop.apps.symfonycasts.com/token");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		String token = response.jsonPath().getString("access_token");
		
		System.out.println(token);
		
		
	}
	
	
	
	
	
	
	
	

}
