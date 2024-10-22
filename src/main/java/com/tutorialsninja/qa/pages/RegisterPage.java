package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage
{
	WebDriver driver;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Objects
	@FindBy(id = "input-firstname")
	private WebElement firstName;
	
	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(id = "input-email")
	private WebElement email;
	
	@FindBy(id = "input-telephone")
	private WebElement telephone;
	
	@FindBy(id = "input-password")
	private WebElement password;
	
	@FindBy(id = "input-confirm")
	private WebElement confirm;
	
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	private WebElement newsletterCheckCircle;
	
	@FindBy(name = "agree")
	private WebElement privacyPolicyCheckBox;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement continueButton;
	
			//Messages
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailAlreadyRegisteredWarningText;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarningText;
	
	@FindBy(xpath = "//div[text()='First Name must be between 1 and 32 characters!']")
	private WebElement firstNameText;
	
	@FindBy(xpath = "//div[text()='Last Name must be between 1 and 32 characters!']") 
	private WebElement lastNameText;
	
	@FindBy(xpath = "//div[text()='E-Mail Address does not appear to be valid!']")
	private WebElement emailAddressText;
	
	@FindBy(xpath = "//div[text()='Telephone must be between 3 and 32 characters!']")
	private WebElement telephoneText;
	
	@FindBy(xpath = "//div[text()='Password must be between 4 and 20 characters!']")
	private WebElement passwordText;
	
	//Actions
	public void clickOnNewsletterCheckCircle()
	{
		newsletterCheckCircle.click();
	}
	public SuccessPage sendingData(String firstname, String lastname, String emailAddress, String telePhone, String passWord, String confirmPassword)
	{
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		email.sendKeys(emailAddress);
		telephone.sendKeys(telePhone);
		password.sendKeys(passWord);
		confirm.sendKeys(confirmPassword);
		privacyPolicyCheckBox.click();
		continueButton.click();
		return new SuccessPage(driver);
	}
	public void clickOnContinueButton()
	{
		continueButton.click();
	}
	
			//Messages
	public String retrieveEmailAlreadyRegisteredWarningMessage()
	{
		return emailAlreadyRegisteredWarningText.getText();
	}
	public String retrievePrivacyPolicyWarningMessage()
	{
		return privacyPolicyWarningText.getText();
	}
	public String retrieveFirstNameMessage()
	{
		return firstNameText.getText();
	}
	public String retrieveLastNameMessage()
	{
		return lastNameText.getText();
	}
	public String retrieveEmailAddressMessage()
	{
		return emailAddressText.getText();
	}
	public String retrieveTelephoneMessage()
	{
		return telephoneText.getText();
	}
	public String retrievePasswordMessage()
	{
		return passwordText.getText();
	}
}