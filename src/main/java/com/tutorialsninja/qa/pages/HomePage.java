package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage
{
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Objects
	@FindBy(linkText = "My Account")
	private WebElement myAccountDropMenu;
	
			//Login
	@FindBy(linkText = "Login")
	private WebElement loginOption;
	
			//Register
	@FindBy(linkText = "Register")
	private WebElement registerOption;
	
			//Search
	@FindBy(name = "search")
	private  WebElement searchBox;
	
	@FindBy(xpath = "//button[@type='button']//i[@class='fa fa-search']")
	private WebElement searchButton;
	
	//Actions
			//Login
	public LoginPage navigateToLoginPage()
	{
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
			//Register
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
	
			//Search
	public SearchPage navigateToSearchPage(String product)
	{
		searchBox.sendKeys(product);
		searchButton.click();
		return new SearchPage(driver);
	}
	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
}