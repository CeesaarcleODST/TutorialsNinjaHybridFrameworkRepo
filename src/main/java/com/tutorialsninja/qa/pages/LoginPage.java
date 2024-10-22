package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage
{
	WebDriver driver;
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Objects
	@FindBy(id = "input-email")
	private WebElement emailField;
	
	@FindBy(id = "input-password")
	private WebElement passwordField;
	
	@FindBy(xpath = "//input[@value='Login']")
	private WebElement loginButton;
	
			//Message
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement noMatchEmailPasswordWarning;
	
	//Actions
	public AccountPage sendingCredentials(String email, String password)
	{
		emailField.sendKeys(email);
		passwordField.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	public void clickOnLoginButton()
	{
		loginButton.click();
	}
	
			//Messages
	public String noMatchEmailPasswordWarningMessage()
	{
		return noMatchEmailPasswordWarning.getText();
	}
}