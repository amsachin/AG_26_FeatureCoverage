package com.example.petclinic.testng.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class VisitFormApiTest {

	@BeforeClass
	public void configureRestAssured() {
		RestAssured.baseURI = System.getProperty("app.baseUrl", "http://localhost:8080");
	}

	@Test(groups = { "teamPetClinic", "appPetClinic", "featureVisits", "funcVisitManagement",
			"subFuncOpenNewVisitForm" })
	public void shouldGetNewVisitFormForOwnerPet() {
		given().accept(ContentType.HTML)
			.when()
			.get("/owners/{ownerId}/pets/{petId}/visits/new", 1, 1)
			.then()
			.statusCode(200)
			.contentType(containsString("text/html"))
			.body(containsString("George Franklin"))
			.body(containsString("Leo"))
			.body(containsString("Previous Visits"))
			.body(containsString("Add Visit"));
	}

}
