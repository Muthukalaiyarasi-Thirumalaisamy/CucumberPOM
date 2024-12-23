package com.TestRunner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/resources/Features/Products.feature",
		glue={"stepDefinition","AppHooks"},
		plugin="pretty"
		)

public class TestRunner {

	
}