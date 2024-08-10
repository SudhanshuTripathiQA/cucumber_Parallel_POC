package com.qa.test.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features", 
			glue = "com.qa.news.steps", plugin = { "pretty",
		"html:target/cucumber-reports" }, monochrome = true)

public class TestRunner {
}
