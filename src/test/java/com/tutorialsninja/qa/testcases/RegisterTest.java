package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;
import com.tutorialsninja.qa.pages.SuccessPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base
{
	public RegisterTest()
	{
		super();
	}
	
	WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	SuccessPage successPage;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL();
	}
	
	@Test(priority = 1)
	public void verifyRegisterWithMandatoryFields()
	{
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
		successPage = registerPage.sendingData(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), dataProp.getProperty("password"), dataProp.getProperty("password"));
		
		Assert.assertTrue(successPage.retrieveAccountCreatedMessage().contains(dataProp.getProperty("accountCreatedTextMessage")));
	}
	
	@Test(priority = 2)
	public void verifyRegisterProvidingAllFields()
	{
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
		registerPage.clickOnNewsletterCheckCircle();
		successPage = registerPage.sendingData(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("telephone"), dataProp.getProperty("password"), dataProp.getProperty("password"));
		
		Assert.assertTrue(successPage.retrieveAccountCreatedMessage().contains(dataProp.getProperty("accountCreatedTextMessage")));
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		return Utilities.getTestDataFromExcelFile("Login");
	}
	
	@Test(priority = 3, dataProvider = "validCredentialsSupplier")
	public void verifyRegisterWithDuplicateEmail(String email, String password)
	{
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
		registerPage.sendingData(dataProp.getProperty("firstName"), dataProp.getProperty("lastName"), email, dataProp.getProperty("telephone"), dataProp.getProperty("password"), dataProp.getProperty("password"));
		
		Assert.assertTrue(registerPage.retrieveEmailAlreadyRegisteredWarningMessage().contains(dataProp.getProperty("emailAlreadyRegisteredWarningMessage")));
	}
	
	@Test(priority = 4)
	public void verifyRegisterWithoutProvidingAnyField()
	{
		homePage = new HomePage(driver);
		registerPage = homePage.navigateToRegisterPage();
		
		registerPage.clickOnContinueButton();
		
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarningMessage().contains(dataProp.getProperty("privacyPolicyWarningMessage")));
		
		Assert.assertTrue(registerPage.retrieveFirstNameMessage().contains(dataProp.getProperty("firstNameTextMessage")));
		
		Assert.assertTrue(registerPage.retrieveLastNameMessage().contains(dataProp.getProperty("lastNameTextMessage")));
		
		Assert.assertTrue(registerPage.retrieveEmailAddressMessage().contains(dataProp.getProperty("emailAddressTextMessage")));
		
		Assert.assertTrue(registerPage.retrieveTelephoneMessage().contains(dataProp.getProperty("telephoneTextMessage")));
		
		Assert.assertTrue(registerPage.retrievePasswordMessage().contains(dataProp.getProperty("passwordTextMessage")));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}