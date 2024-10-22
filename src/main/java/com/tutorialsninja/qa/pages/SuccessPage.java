package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPage
{
	WebDriver driver;
	
	public SuccessPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Objects
	@FindBy(xpath = "//h1[text()]")
	private WebElement accountCreatedText;
	
	//Actions
	public String retrieveAccountCreatedMessage()
	{
		return accountCreatedText.getText();
	}
}