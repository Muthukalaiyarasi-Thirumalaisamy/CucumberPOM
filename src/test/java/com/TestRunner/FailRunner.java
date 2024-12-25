package com.TestRunner;

import org.junit.AfterClass;
import org.junit.runner.RunWith;

import AppHooks.ApplicationHooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerunfailedtest.txt",
        glue = {"stepDefinition", "AppHooks"},
        plugin = {
                "pretty",
                "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
                "html:target/cucumber-report.html",
                "rerun:target/rerunfailedtest.txt"
        }
)
public class FailRunner {

    @AfterClass
    public static void tearDownAll() {
        // Flush Extent Reports after all tests
        ApplicationHooks.flushReports();
    }

}
