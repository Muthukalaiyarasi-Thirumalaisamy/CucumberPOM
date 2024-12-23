package stepDefinition;

import java.util.List;
import java.util.Map;

import com.qa.factory.DriverFactory;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import junit.framework.Assert;

public class ProductsPageStepDefinition {

	private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
	private ProductsPage productsPage;

@Given("user has already logged into application")
public void user_has_already_logged_into_application(DataTable dataTable) {
    
	List<Map<String,String>> dataList = dataTable.asMaps();
	String Username = dataList.get(0).get("username");
	String Password = dataList.get(0).get("password");
	DriverFactory.getDriver().get("https://www.saucedemo.com/");
	productsPage = loginPage.doLogin(Username, Password);

}

@Given("user is on Products page")
public void user_is_on_products_page() {
	String Title= productsPage.GetProductsPagetitle();
	System.out.println("Products page title is " +Title);
	
}

@SuppressWarnings("deprecation")
@Then("user get prodducts list")
public void user_get_prodducts_list(DataTable prodcutTable) {
	
    List<String> ExpectedProductsList = prodcutTable.asList();
    System.out.println("Expected product lists are " +ExpectedProductsList);
    
    List<String> ActualProductsList = productsPage.getProductsList();
    System.out.println("Expected product lists are " +ActualProductsList);
   Assert.assertTrue(ExpectedProductsList.containsAll(ActualProductsList));
}

@SuppressWarnings("deprecation")
@Then("products count should be {int}")
public void products_count_should_be(Integer expectedProductsCount) {
    Assert.assertTrue(productsPage.getProductsCount() == expectedProductsCount);
}



}
