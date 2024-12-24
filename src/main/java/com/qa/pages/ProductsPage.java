package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {
	
	public WebDriver driver;
	private By productsList= By.cssSelector(".inventory_item_name");
	
    private By cartContainer =By.id("shopping_cart_container");
    private By addToCartButton = By.xpath("//div[text()='Sauce Labs Fleece Jacket']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']");

        
	public ProductsPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String GetProductsPagetitle()
	{
		return driver.getTitle();
	}
	public int getProductsCount()
	{
		return driver.findElements(productsList).size();
	}
	
	public List<String> getProductsList()
	{
		List<String> productList = new ArrayList<>();
		List<WebElement> productsNameList = driver.findElements(productsList);
		for(WebElement e: productsNameList)
		{
			String text = e.getText().trim();
			System.out.println(text);
			productList.add(text);
		}
		
		return productList;
	
	}
	public void addProductToCart()
	{
		driver.findElement(addToCartButton).click();
	}
	
	public Integer getCartCount()
	{
		WebElement CartCount = driver.findElement(By.xpath("//span[@class='shopping_cart_badge']"));
		String ActualCartCount = CartCount.getText();
		return Integer.parseInt(ActualCartCount);	
	}
		
	
//	public CartPage ClickCartContainerIfDisplays()
//	{
//		boolean flag=driver.findElement(cartContainer).isDisplayed();
//		if(flag)
//		{
//			driver.findElement(cartContainer).click();
//		}
//		return new CartPage(driver);
//		
//	}
	
	/*when its string input from feature file
	 
	public CartPage addProductsToCart(String productToBeAdded) throws InterruptedException {
	    List<WebElement> productList = driver.findElements(productsList);
	    for (WebElement product : productList) {
	        String productName = product.getText();
	        if (productName.equals(productToBeAdded)) {
	            driver.findElement(addToCartButton).click();}}
		driver.findElement(cartContainer).click();
		Thread.sleep(30);		
		return new CartPage(driver);}*/
	
	public CartPage addProductsToCart(List<String> productsToBeAdded) throws InterruptedException {
	    List<WebElement> productList = driver.findElements(productsList);
	   
	    for (String productToBeAdded : productsToBeAdded) {
	        boolean productFound = false;
	        for (WebElement product : productList) {
	            String productName = product.getText().trim();  // Clean the product name
	            if (productName.equals(productToBeAdded)) {
	                System.out.println("Adding product to cart: " + productName);
	                
	 driver.findElement(By.xpath("//div[text()='"+productName+"']/ancestor::div[@class='inventory_item']//button[text()='Add to cart']"));
	                
	                productFound = true;
	                break;  
	            }
	        }
	        if (!productFound) {
	            System.out.println("Product not found: " + productToBeAdded);
	        }
	    }

	    driver.findElement(cartContainer).click();
	    Thread.sleep(30);
	    return new CartPage(driver);  
	}

}
