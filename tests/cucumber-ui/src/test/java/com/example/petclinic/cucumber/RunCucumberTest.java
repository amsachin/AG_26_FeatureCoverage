package com.example.petclinic.cucumber;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.example.petclinic.cucumber.steps",
		plugin = { "pretty", "html:target/cucumber-reports/cucumber.html",
				"json:target/cucumber-reports/cucumber.json" })
public class RunCucumberTest {

}
