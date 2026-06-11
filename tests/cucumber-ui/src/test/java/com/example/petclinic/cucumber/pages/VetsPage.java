package com.example.petclinic.cucumber.pages;

import java.net.URI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class VetsPage {

	private final WebDriver driver;

	private final URI baseUri;

	public VetsPage(WebDriver driver, URI baseUri) {
		this.driver = driver;
		this.baseUri = baseUri;
	}

	public void open() {
		this.driver.get(this.baseUri.resolve("/vets.html").toString());
	}

	public String heading() {
		return this.driver.findElement(By.tagName("h2")).getText();
	}

	public String vetsTableText() {
		return this.driver.findElement(By.id("vets")).getText();
	}

}
