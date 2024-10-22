package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base
{
	public LoginTest()
	{
		super();
	}
	
	WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL();
	}
	
	@DataProvider(name = "validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		return Utilities.getTestDataFromExcelFile("Login");
	}
	
	@Test(priority = 1, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email, String password)
	{
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		
		AccountPage accountPage = loginPage.sendingCredentials(email, password);
				
		Assert.assertTrue(accountPage.retrieveEditYourAccountInformationOption());
	}
	
	@Test(priority = 2)
	public void verifyLoginWithInvalidCredentials()
	{
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		
		loginPage.sendingCredentials(Utilities.generateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		
		Assert.assertTrue(loginPage.noMatchEmailPasswordWarningMessage().contains(dataProp.getProperty("noMatchEmailPasswordWarningMessage")));
	}
	
	@Test(priority = 3, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithValidEmailAndInvalidPassword(String email, String mnah)
	{
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		
		loginPage.sendingCredentials(email, dataProp.getProperty("invalidPassword"));
		
		Assert.assertTrue(loginPage.noMatchEmailPasswordWarningMessage().contains(dataProp.getProperty("noMatchEmailPasswordWarningMessage")));
	}
	
	@Test(priority = 4, dataProvider = "validCredentialsSupplier")
	public void verifyLoginWithInvalidEmailAndValidPassword(String mnah, String password)
	{
		homePage = new HomePage(driver);
		loginPage = homePage.navigateToLoginPage();
		
		loginPage.sendingCredentials(Utilities.generateEmailWithTimeStamp(), password);
		
		Assert.assertTrue(loginPage.noMatchEmailPasswordWarningMessage().contains(dataProp.getProperty("noMatchEmailPasswordWarningMessage")));
	}
	
	@Test(priority = 5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		homePage = new HomePage(driver);
		
		loginPage = homePage.navigateToLoginPage();
		
		loginPage.clickOnLoginButton();
		
		Assert.assertTrue(loginPage.noMatchEmailPasswordWarningMessage().contains(dataProp.getProperty("noMatchEmailPasswordWarningMessage")));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}