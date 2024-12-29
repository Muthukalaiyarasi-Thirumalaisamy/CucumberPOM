package TestRunner;

import org.junit.runner.RunWith;

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

   
}
