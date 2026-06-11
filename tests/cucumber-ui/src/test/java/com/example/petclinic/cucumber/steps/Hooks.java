package com.example.petclinic.cucumber.steps;

import com.example.petclinic.cucumber.driver.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {

	private final TestContext context;

	public Hooks(TestContext context) {
		this.context = context;
	}

	@Before
	public void createDriver() {
		WebDriver driver = DriverFactory.createDriver();
		this.context.setDriver(driver);
	}

	@After
	public void quitDriver() {
		WebDriver driver = this.context.getDriver();
		if (driver != null) {
			driver.quit();
		}
	}

}
