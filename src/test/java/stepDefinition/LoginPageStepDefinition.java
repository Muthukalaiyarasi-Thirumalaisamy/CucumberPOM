package stepDefinition;

import org.junit.Assert;

import com.qa.factory.DriverFactory;
import com.qa.pages.LoginPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginPageStepDefinition {
	private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
	private static String Title;
	
@Given("user is on login page")
public void user_is_on_login_page() {
	
	DriverFactory.getDriver().get("https://www.saucedemo.com/");
}

@When("user gets the title of the page")
public void user_gets_the_title_of_the_page() {
	Title= loginPage.GetPageTitle();
	System.out.println("Title is "+Title);
}

@Then("page title should be {string}")
public void page_title_should_be(String ExpectedTitle)
{
	Assert.assertEquals(ExpectedTitle, Title);
}

@When("user enters username {string}")
public void user_enters_username(String username) {
	loginPage.getUsername(username);
 
}
@When("user enters password {string}")
public void user_enters_password(String password) {
	loginPage.getPassword(password);
}

@When("user clicks on login button")
public void user_clicks_on_login_button() {
	loginPage.ClickOnLogin();
}

	
}

