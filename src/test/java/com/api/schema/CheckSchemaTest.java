package com.api.schema;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class CheckSchemaTest {
	
	@Test
	public void bookingSchemaTest() {
		
		given().log().all()
			.contentType(ContentType.JSON)
			.body(new File(".\\src\\test\\java\\com\\api\\datafiles\\booking.json"))
		.when().log().all()
			.post("https://restful-booker.herokuapp.com/booking")
		.then().log().all()
			.assertThat()
				.statusCode(200)
			.and()
				.body(matchesJsonSchemaInClasspath("bookingsSchema.json"));
		
	}

}
