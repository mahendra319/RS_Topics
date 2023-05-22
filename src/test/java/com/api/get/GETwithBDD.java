package com.api.get;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GETwithBDD {
	
	
	@Test
	public void getApiTest1() {
		
		
		given().log().all()
		.when().log().all()
			.get("http://ergast.com/api/f1/2017/circuits.json")
		.then().log().all()
			.assertThat()
				.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
		
	}
	
	@Test
	public void getApiTest2() {
		
		Response res = given().log().all()
		.when().log().all()
			.get("http://ergast.com/api/f1/2017/circuits.json");
		
		int statusCode = res.getStatusCode();
		System.out.println("Api status code is: "+statusCode);
		
		Assert.assertEquals(statusCode, 200);
		
		System.out.println(res.prettyPrint());
		
		String str = res.jsonPath().getString("MRData.total");
		System.out.println("Total circuits are: "+str);
		
	}
	
	
	@Test
	public void getApiTest3() {
		
		RestAssured.baseURI = "http://ergast.com";
		
		given()
		.when()
			.get("/api/f1/2017/circuits.json")
		.then()
			.assertThat()
				.statusCode(200)
			.and()
				.contentType(ContentType.JSON)
			.and()
				.header("Content-Length", equalTo("4552"));
	}
	
	
	@Test
	public void getApiVerifyMd5Test() {
		
		
		//RestAssured.baseURI = "http://md5.jsontest.com";
		
		given().log().all()
			.queryParam("text", "test")
		.when()
			.get("http://md5.jsontest.com")
		.then()
			.assertThat()
				.statusCode(200)
			.and()
				.body("md5", equalTo("098f6bcd4621d373cade4e832627b4f6"));
			
	}
	
	
	@DataProvider(name ="CircuitInfo")
	public Object[][] getCircuitInfo() {
		
		
		return new Object [][] {
			
			{"2017","20"},
			{"2016","21"},
			{"1966","9"}
			
		};
	}
	
	@Test(dataProvider="CircuitInfo")
	public void getTotalCircuitPerYear(String cicuitYear, String numberOfRaces) {
		
		given().log().all()
			.pathParam("seasonYear", cicuitYear)
		.when().log().all()
			.get("http://ergast.com/api/f1/{seasonYear}/circuits.json")
		.then().log().all()
			.assertThat()
				.body("MRData.total", equalTo(numberOfRaces));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
