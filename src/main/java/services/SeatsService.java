package services;

import static io.restassured.RestAssured.given;

import org.apache.http.HttpStatus;
import org.json.JSONObject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SeatsService {

	public static String BASE_API_URL = "http://inventory-service.tixuk.io/api/v4/";

	public SeatsService() {

	}

	public static void getSeats(String date, String time) {

		RestAssured.baseURI = BASE_API_URL;

		Response response = given().contentType(ContentType.JSON).header("affiliateId", "encoretickets").when()
				.get("products/6362/areas?date=" + date + "&time=" + time + "&quantity=2&includeSeatsAttributes=true")
				.then().log().ifError().assertThat().statusCode(HttpStatus.SC_OK).extract().response();

		JSONObject inputJSONOBject = new JSONObject(response.getBody().asString());
		utils.JsonParser.getKey(inputJSONOBject, "seatLumps");

	}

	public static void getRestrictedSeats(String date, String time) {

		RestAssured.baseURI = BASE_API_URL;

		Response response = given().contentType(ContentType.JSON).header("affiliateId", "encoretickets").when()
				.get("products/6362/areas?date=" + date + "&time=" + time + "&quantity=2&includeSeatsAttributes=true")
				.then().log().ifError().assertThat().statusCode(HttpStatus.SC_OK).extract().response();

		JSONObject inputJSONOBject = new JSONObject(response.asString());
		utils.JsonParser.getKey(inputJSONOBject, "seats");

	}

}
