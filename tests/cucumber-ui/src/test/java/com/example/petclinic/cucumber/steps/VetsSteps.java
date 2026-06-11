package com.example.petclinic.cucumber.steps;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import com.example.petclinic.cucumber.config.TestConfig;
import com.example.petclinic.cucumber.pages.VetsPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class VetsSteps {

	private final TestContext context;

	private VetsPage vetsPage;

	public VetsSteps(TestContext context) {
		this.context = context;
	}

	@Given("the PetClinic application is running")
	public void thePetClinicApplicationIsRunning() throws IOException, InterruptedException {
		URI baseUri = TestConfig.baseUri();
		HttpRequest request = HttpRequest.newBuilder(baseUri).timeout(Duration.ofSeconds(5)).GET().build();
		HttpResponse<Void> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.discarding());
		int statusCode = response.statusCode();
		assertTrue("Expected PetClinic to be running at " + baseUri + " but received HTTP " + statusCode,
				statusCode >= 200 && statusCode < 500);
	}

	@When("I open the vets page")
	public void iOpenTheVetsPage() {
		this.vetsPage = new VetsPage(this.context.getDriver(), TestConfig.baseUri());
		this.vetsPage.open();
	}

	@Then("the vets page should be displayed")
	public void theVetsPageShouldBeDisplayed() throws InterruptedException {
		Thread.sleep(1000);
		assertTrue("Expected vets page heading to contain Veterinarians but was: " + this.vetsPage.heading(),
				this.vetsPage.heading().contains("Veterinarians"));
	}

	@Then("the vets table should include {string}")
	public void theVetsTableShouldInclude(String expectedVetName) {
		String tableText = this.vetsPage.vetsTableText();
		assertTrue("Expected vets table to include " + expectedVetName + " but table was: " + tableText,
				tableText.contains(expectedVetName));
	}
}
