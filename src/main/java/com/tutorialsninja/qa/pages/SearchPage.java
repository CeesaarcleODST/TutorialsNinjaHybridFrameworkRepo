package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage
{
	WebDriver driver;
	
	public SearchPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Objects
	@FindBy(linkText = "HP LP3065")
	private WebElement validProduct;
	
			//Messages
	@FindBy(xpath = "//h2/following-sibling::p")
	private WebElement noProductMessage;
	
	//Actions
	public boolean retrieveStatusValidProduct()
	{
		return validProduct.isDisplayed();
	}
	
			//Messages
	public String retrieveNoProductMessage()
	{
		return noProductMessage.getText();
	}
}