package com.example.petclinic.cucumber.pages;

import java.net.URI;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class OwnersPage {

	private final WebDriver driver;

	private final URI baseUri;

	public OwnersPage(WebDriver driver, URI baseUri) {
		this.driver = driver;
		this.baseUri = baseUri;
	}

	public void openOwnersList() {
		this.driver.get(this.baseUri.resolve("/owners").toString());
		waitForHeading();
	}

	public void openOwnerDetails(int ownerId) {
		this.driver.get(this.baseUri.resolve("/owners/" + ownerId).toString());
		waitForHeading();
	}

	public void openFindOwners() {
		this.driver.get(this.baseUri.resolve("/owners/find").toString());
		waitForHeading();
	}

	public String heading() {
		return this.driver.findElement(By.tagName("h2")).getText();
	}

	public String ownersTableText() {
		return this.driver.findElement(By.id("owners")).getText();
	}

	public String ownerDetailsText() {
		return this.driver.findElement(By.tagName("body")).getText();
	}

	public boolean findOwnersFormDisplayed() {
		return this.driver.findElement(By.id("search-owner-form")).isDisplayed();
	}

	private void waitForHeading() {
		new WebDriverWait(this.driver, Duration.ofSeconds(5))
			.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h2")));
	}

}
