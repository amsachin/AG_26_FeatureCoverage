package com.example.petclinic.cucumber.config;

import java.net.URI;

public final class TestConfig {

	private TestConfig() {
	}

	public static URI baseUri() {
		String baseUrl = System.getProperty("app.baseUrl", "http://localhost:8080");
		return URI.create(baseUrl);
	}

	public static String browser() {
		return System.getProperty("browser", "chrome");
	}

	public static boolean headless() {
		return Boolean.parseBoolean(System.getProperty("headless", "true"));
	}

}
