package com.api.responsespecbuilder;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;


public class ResponseSpecBuilderTest {
	
	ResponseSpecBuilder resSpec = new ResponseSpecBuilder();
	
	ResponseSpecification spec_200_OK=	 resSpec.expectContentType(ContentType.JSON)
							.expectStatusCode(200).build();
	
	ResponseSpecification spec_401_AuthFail=	 resSpec.expectContentType(ContentType.JSON)
			.expectStatusCode(401).build();
	
	@Test
	public void response_Spec_Builder_Test() {
		
		
		
		given()
		.when()
			.get("http://ergast.com/api/f1/2017/circuits.json")
		.then()
			.assertThat()
				.spec(spec_200_OK);
		
		
	}
	
	@Test
	public void response_Spec_Builder_401_Test() {
		
		
		
		given()
		.when()
			.get("http://ergast.com/api/f1/2017/circuits.json")
		.then()
			.assertThat()
				.spec(spec_401_AuthFail);
		
		
	}

}
