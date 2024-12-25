package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

	private WebDriver driver;
    private By TitleXpath = By.xpath("//span[text()='Your Cart']");
    private By ContinueShopping = By.id("continue-shopping");
    private By checkout = By.id("checkout");
    private By CartProductName = By.cssSelector(".inventory_item_name");
    private By cartContainer =By.id("shopping_cart_container");
    
    public CartPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String GetCartPagetitle()
	{
		WebElement CartTitle =driver.findElement(TitleXpath);
		String CartPageTitle=CartTitle.getText();
		return CartPageTitle;
	} 
	
	public int getCartQuantity()
	{
		List<WebElement> InCartQuantity = driver.findElements(CartProductName);
		
		return InCartQuantity.size();
	}
	
	public void ClickContinueShopping()
	{
	driver.findElement(ContinueShopping).click();	
	}
	
	public List<String> GetProductAddedInCart() {
	    List<String> cartProductNames = new ArrayList<>();
	    List<WebElement> ProductAddedIn = driver.findElements(CartProductName);
	    
	    for (WebElement product : ProductAddedIn) {
	        cartProductNames.add(product.getText().trim());
	    }
	    
	    return cartProductNames;
	}
	/*for string input 
	public String GetProductAddedInCart()
	{
         WebElement ProductAddedIn = driver.findElement(CartProductName);
         String ProductAddedInCart=ProductAddedIn.getText();
         return ProductAddedInCart;
	}*/
    
	public void ClickCheckOut()
	{
		driver.findElement(checkout).click();
	}

	public boolean isCartContainerDisplayed()
	{
		return driver.findElement(cartContainer).isDisplayed();
		
	}
	
}