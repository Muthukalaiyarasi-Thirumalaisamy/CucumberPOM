package stepDefinition;

import java.util.List;
import java.util.Map;

import org.junit.Assert;

import org.openqa.selenium.WebDriver;


import com.qa.factory.DriverFactory;
import com.qa.pages.LoginPage;
import com.qa.pages.ProductsPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ProductsPageStepDefinition {

	private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
	private ProductsPage productsPage;
    WebDriver driver;

@Given("user has already logged into application")
public void user_has_already_logged_into_application(DataTable dataTable) {
    
	List<Map<String,String>> dataList = dataTable.asMaps();
	String Username = dataList.get(0).get("username");
	String Password = dataList.get(0).get("password");
	WebDriver driver =DriverFactory.getDriver();
	driver.get("https://www.saucedemo.com/");
	productsPage = loginPage.doLogin(Username, Password);

}

@Given("user is on Products page")
public void user_is_on_products_page() {
	String Title= productsPage.GetProductsPagetitle();
	System.out.println("Products page title is " +Title);
	
}

@Given("user gets products list")
public void user_gets_products_list(DataTable prodcutTable) {
	
    List<String> ExpectedProductsList = prodcutTable.asList();
    System.out.println("Expected product lists are " +ExpectedProductsList);
    
    List<String> ActualProductsList = productsPage.getProductsList();
    System.out.println("Expected product lists are " +ActualProductsList);
   Assert.assertTrue(ExpectedProductsList.containsAll(ActualProductsList));
}


@Then("products count should be {int}")
public void products_count_should_be(Integer expectedProductsCount) {
    int yourCount = productsPage.getProductsCount();
    if(yourCount==expectedProductsCount)
    {
    	System.out.println("Product count is " +yourCount);
    }
}


@When("user searches for {string}")
public void user_searches_for(String productName) {
	List<String> productList1 = productsPage.getProductsList();
boolean isProductFound = productList1.contains(productName);
    
    if (isProductFound) {
        System.out.println("Product found: " + productName);
        Assert.assertTrue("Product should be found", isProductFound);
    } else {
        System.out.println("Product not found: " + productName);
}

}
@Then("if product name {string} matches actual products, it should be added to the cart")
public void if_product_name_matches_actual_products_it_should_be_added_to_the_cart(String productToBeAdded) {
	List<String> actualProducts = productsPage.getProductsList();
	if (actualProducts.contains(productToBeAdded)) {
		productsPage.addProductToCart();
		System.out.println("Product added to cart: " + productToBeAdded);
	} else {
		throw new AssertionError("Product not found in the actual products list: " + productToBeAdded);
	}
}

@When("cart count should increase by {int}")
public void cart_count_should_increase_by(Integer ExpectedCartcount) {
	Integer actualCartCount = productsPage.getCartCount();
    if(actualCartCount.equals(ExpectedCartcount))
    	
    {
    	System.out.println("Product added to cart successfully and the count of the product is " + actualCartCount);
    }
}

}
