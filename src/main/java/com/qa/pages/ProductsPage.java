package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

	
	//span[text()='Products']
	//div[@class='inventory_list']//div[@class='inventory_item_name ']
	
	private WebDriver driver;
	private By productsList= By.xpath("//div[@class='inventory_list']//div[@class='inventory_item_name ']");
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
			String text = e.getText();
			System.out.println(text);
			productList.add(text);
		}
		
		return productList;
		
		
	}
	
}
