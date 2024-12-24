package stepDefinition;
import java.util.List;
import org.junit.Assert;
import com.qa.factory.DriverFactory;
import com.qa.pages.CartPage;
import com.qa.pages.ProductsPage;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CartPageStepDefinition {
	
	//private LoginPage loginPage=new LoginPage(DriverFactory.getDriver());
	private ProductsPage productsPage=new ProductsPage(DriverFactory.getDriver());
	private CartPage cartPage=new CartPage(DriverFactory.getDriver());
    private String ActualCartPageTitle;
    
    /*when its string input from feature file
 @When("user is added product {string} to cart")
    public void user_is_added_products_to_cart(String expectedCartProduct) throws InterruptedException {
	
	 cartPage=productsPage.addProductsToCart(expectedCartProduct);
    } */
    
@Then("user is on Cart page")
public void user_is_on_cart_page() {
	Assert.assertTrue(cartPage.isCartContainerDisplayed());
}
    
@When("user should get the title of cart page")
public void user_should_get_the_title_of_cart_page() {
	ActualCartPageTitle =cartPage.GetCartPagetitle(); 
	
	System.out.println(" Cart Page title is " + ActualCartPageTitle);
	
}

@Then("title of the cart page should be {string}")
public void title_of_the_cart_page_should_be(String ExpectedCartPageTitle) {
	
	if(ExpectedCartPageTitle.equals(ActualCartPageTitle))
	{
		System.out.println("user in expected page");
	}
	
}


@When("user is added product below to cart")
public void user_is_added_product_below_to_cart(DataTable dataTable) throws InterruptedException {
	List<String> ExpectedProductsList = dataTable.asList();
	cartPage=productsPage.addProductsToCart(ExpectedProductsList);
}

@When("product added should be same as expected products")
public void product_added_should_be_same_as_expected_products(DataTable dataTable) {
    List<String> ExpectedProductsInCart =dataTable.asList();
    List<String> actualProductsInCart = cartPage.GetProductAddedInCart(); 
   
    Assert.assertTrue("The products in the cart do not match the expected products!",
    		actualProductsInCart.containsAll(ExpectedProductsInCart));
}


/*when input is string in feature file
@When("product added should be same as expected product {string}")
public void product_added_should_be_same_as_expected_product(String ExpectedproductAddedIncart) 
{
	String ActualProductAddedInCart =cartPage.GetProductAddedInCart();
	if(ActualProductAddedInCart.equals(ExpectedproductAddedIncart))
	{
		System.out.println("Products added in cart as selected " + ActualProductAddedInCart);
	}

}*/

@Then("user should get the count of the product added in cart as {int}")
public void user_should_get_the_count_of_the_product_added_in_cart(Integer ExpectedCartQuantity) {
	
int ActualCartQuantity = cartPage.getCartQuantity();

	if(ExpectedCartQuantity == ActualCartQuantity)
	{
		cartPage.ClickCheckOut();
		System.out.println("product checked out and ActualCartQuantity is " + ActualCartQuantity);
	}
//else
//{
//	cartPage.ClickContinueShopping();
//	System.out.println("product not checked out and continue shopping");
//
//}
}
}