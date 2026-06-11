package com.example.petclinic.cucumber.driver;

import com.example.petclinic.cucumber.config.TestConfig;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public final class DriverFactory {

	private DriverFactory() {
	}

	public static WebDriver createDriver() {
		String browser = TestConfig.browser().toLowerCase();
		return switch (browser) {
			case "firefox" -> new FirefoxDriver(firefoxOptions());
			case "chrome" -> new ChromeDriver(chromeOptions());
			default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
		};
	}

	private static ChromeOptions chromeOptions() {
		ChromeOptions options = new ChromeOptions();
		if (TestConfig.headless()) {
			options.addArguments("--headless=new");
		}
		options.addArguments("--disable-gpu", "--no-sandbox", "--window-size=1280,900");
		return options;
	}

	private static FirefoxOptions firefoxOptions() {
		FirefoxOptions options = new FirefoxOptions();
		if (TestConfig.headless()) {
			options.addArguments("-headless");
		}
		return options;
	}

}
