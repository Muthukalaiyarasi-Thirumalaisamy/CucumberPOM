package AppHooks;

import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.factory.DriverFactory;
import com.qa.util.ConfigReader;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class ApplicationHooks {

    private DriverFactory driverFactory;
    private WebDriver driver;
    private ConfigReader configReader;
    private Properties prop;
    private static ExtentReports extent; // ExtentReports instance
    private ExtentTest test;             // ExtentTest instance for logging

    // Static block to initialize ExtentReports
    static {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/SparkReport/Spark.html");
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("Test Automation Report");
        sparkReporter.config().setReportName("Cucumber Test Execution");

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        // Add System Information
        extent.setSystemInfo("OS", "Windows");
        extent.setSystemInfo("User", "Muthukalaiyarasi Thirumalaisamy");
        extent.setSystemInfo("Role", "Test Engineer");
        extent.setSystemInfo("Team", "Simplification Two Pot/ QA");
        extent.setSystemInfo("AppName", "Cucumber Automation for Swag Labs");
    }

    @Before(order = 0)
    public void getProperty() {
        configReader = new ConfigReader();
        prop = configReader.init_prob();
    }

    @Before(order = 1)
    public void launchBrowser(Scenario scenario) {
        String browserName = prop.getProperty("browser");
        driverFactory = new DriverFactory();
        driver = driverFactory.init_driver(browserName);

        // Initialize ExtentTest for this scenario
        test = extent.createTest(scenario.getName());
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                // Capture screenshot as a Base64 string
                String base64Screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);

                // Attach screenshot to Cucumber report
                String screenshotName = scenario.getName().replaceAll(" ", "_");
                scenario.attach(base64Screenshot.getBytes(), "image/png", screenshotName);

                // Log the failure in Extent report and attach the screenshot
                test.log(Status.FAIL, "Test Failed. Screenshot below:");
                test.addScreenCaptureFromBase64String(base64Screenshot, "Failed Step Screenshot");
            } catch (Exception e) {
                test.log(Status.FAIL, "Failed to attach screenshot due to exception: " + e.getMessage());
            }
        } else {
            test.log(Status.PASS, "Test Passed.");
        }
    }

    @After(order = 0)
    public void quitBrowser() {
        driver.quit();
    }

        
}
