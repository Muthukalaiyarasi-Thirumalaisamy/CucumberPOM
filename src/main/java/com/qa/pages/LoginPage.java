package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

	private WebDriver driver;
	
	
	//1.By locators
	private By UserName = By.id("user-name");
	private By passWord = By.id("password");
	private By LoginButton = By.id("login-button");

	//2.Constructor
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	//3.page actions  
	public String GetPageTitle()
	{
		return driver.getTitle();
	}
	
	public void getUsername(String username)
	{
		driver.findElement(UserName).sendKeys(username);
		
	}
	public void getPassword(String password)
	{
		driver.findElement(passWord).sendKeys(password);
		
	}
	public void ClickOnLogin()
	{
		driver.findElement(LoginButton).click();;
		
	}
	public ProductsPage doLogin(String un, String pwd)
	{
		System.out.println("Username is "+ un + "password is "+ pwd);
		driver.findElement(UserName).sendKeys(un);
		driver.findElement(passWord).sendKeys(pwd);
		driver.findElement(LoginButton).click();;
		return new ProductsPage(driver);
	}
	
}
