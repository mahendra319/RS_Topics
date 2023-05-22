package com.api.get;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetApiWithNonBDD {

	
	@Test
	public void getApiWithNonBDDTest() {
		
		RestAssured.baseURI = "https://www.gorest.co.in";
		
		RequestSpecification request = RestAssured.given();
		
		request.auth().oauth2("7bd741f9ab08d1d9405b0121625eb09077239e8a7e9fb152e066698f81226f49");
		
		//request.body(user);
		
		//request.queryParam("", ""); - query params passing
		
		//using HashMap - Passing query params
		
//		Map<String, String> params = new HashMap<String, String>();
//		params.put("name", "Samhitha");
//		params.put("gender", "female");
//		request.queryParams(params);
		
		Response response = request.get("/public/v2/users");
		
		System.out.println(response.getStatusCode());
		System.out.println(response.prettyPrint());
		
		response.jsonPath().getString("name");
		
	}
}
