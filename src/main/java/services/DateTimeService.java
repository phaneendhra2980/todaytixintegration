package services;


import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.apache.http.HttpStatus;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class DateTimeService {
	
	public static String BASE_API_URL="http://inventory-service.tixuk.io/api/v4/";
	
	public DateTimeService()
	{
		
		
	}

	public static ArrayList<String> getCheckOutJourney(String StartDate, String EndDate) {

		RestAssured.baseURI = BASE_API_URL;
		ArrayList<String> response = given().contentType(ContentType.JSON).header("affiliateId", "encoretickets").when()
				.get("availability/products/6362/quantity/2/from/" + StartDate + "/to/" + EndDate + "?test=98").then()
				.log().ifError().assertThat().statusCode(HttpStatus.SC_OK).extract().path("response.datetime");

		return response;

	}

}
