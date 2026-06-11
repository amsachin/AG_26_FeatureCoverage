package com.example.petclinic.cucumber.steps;

import com.example.petclinic.cucumber.config.TestConfig;
import com.example.petclinic.cucumber.pages.OwnersPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.Assert.assertTrue;

public class OwnersSteps {

	private final TestContext context;

	private OwnersPage ownersPage;

	public OwnersSteps(TestContext context) {
		this.context = context;
	}

	@When("I open the owners page")
	public void iOpenTheOwnersPage() {
		this.ownersPage = new OwnersPage(this.context.getDriver(), TestConfig.baseUri());
		this.ownersPage.openOwnersList();
	}

	@When("I open the owner details page for owner {int}")
	public void iOpenTheOwnerDetailsPageForOwner(int ownerId) {
		this.ownersPage = new OwnersPage(this.context.getDriver(), TestConfig.baseUri());
		this.ownersPage.openOwnerDetails(ownerId);
	}

	@When("I open the find owners page")
	public void iOpenTheFindOwnersPage() {
		this.ownersPage = new OwnersPage(this.context.getDriver(), TestConfig.baseUri());
		this.ownersPage.openFindOwners();
	}

	@Then("the owners page should be displayed")
	public void theOwnersPageShouldBeDisplayed() {
		assertTrue("Expected owners page heading to contain Owners but was: " + this.ownersPage.heading(),
				this.ownersPage.heading().contains("Owners"));
	}

	@Then("the owners table should include {string}")
	public void theOwnersTableShouldInclude(String expectedOwnerName) {
		String tableText = this.ownersPage.ownersTableText();
		assertTrue("Expected owners table to include " + expectedOwnerName + " but table was: " + tableText,
				tableText.contains(expectedOwnerName));
	}

	@Then("the owner details page should be displayed")
	public void theOwnerDetailsPageShouldBeDisplayed() {
		assertTrue("Expected owner details page heading to contain Owner Information but was: "
				+ this.ownersPage.heading(), this.ownersPage.heading().contains("Owner Information"));
	}

	@Then("the owner details should include {string}")
	public void theOwnerDetailsShouldInclude(String expectedText) {
		String detailsText = this.ownersPage.ownerDetailsText();
		assertTrue("Expected owner details to include " + expectedText + " but page was: " + detailsText,
				detailsText.contains(expectedText));
	}

	@Then("the find owners page should be displayed")
	public void theFindOwnersPageShouldBeDisplayed() {
		assertTrue("Expected find owners page heading to contain Find Owners but was: " + this.ownersPage.heading(),
				this.ownersPage.heading().contains("Find Owners"));
	}

	@Then("the find owners form should be displayed")
	public void theFindOwnersFormShouldBeDisplayed() {
		assertTrue("Expected find owners form to be displayed", this.ownersPage.findOwnersFormDisplayed());
	}

}
