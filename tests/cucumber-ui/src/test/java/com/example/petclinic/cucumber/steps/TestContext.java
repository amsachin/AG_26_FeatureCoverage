package com.example.petclinic.cucumber.steps;

import org.openqa.selenium.WebDriver;

public class TestContext {

	private WebDriver driver;

	public WebDriver getDriver() {
		return this.driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
