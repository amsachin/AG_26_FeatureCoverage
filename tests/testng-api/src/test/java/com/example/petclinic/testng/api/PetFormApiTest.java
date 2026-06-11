package com.example.petclinic.testng.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class PetFormApiTest {

	@BeforeClass
	public void configureRestAssured() {
		RestAssured.baseURI = System.getProperty("app.baseUrl", "http://localhost:8080");
	}

	@Test(groups = { "teamPetClinic", "appPetClinic", "featurePets", "funcPetManagement",
			"subFuncOpenNewPetForm" })
	public void shouldGetNewPetFormForOwner() {
		given().accept(ContentType.HTML)
			.when()
			.get("/owners/{ownerId}/pets/new", 1)
			.then()
			.statusCode(200)
			.contentType(containsString("text/html"))
			.body(containsString("George Franklin"))
			.body(containsString("Birth Date"))
			.body(containsString("Add Pet"));
	}

	@Test(groups = { "teamPetClinic", "appPetClinic", "featurePets", "funcPetManagement",
			"subFuncOpenEditPetForm", "bug", "bugHYP-231" })
	public void shouldGetEditPetFormForOwnerPet() {
		given().accept(ContentType.HTML)
			.when()
			.get("/owners/{ownerId}/pets/{petId}/edit", 1, 1)
			.then()
			.statusCode(200)
			.contentType(containsString("text/html"))
			.body(containsString("Pet"))
			.body(containsString("George Franklin"))
			.body(containsString("Leo"))
			.body(containsString("Update Pet"));
	}

}
