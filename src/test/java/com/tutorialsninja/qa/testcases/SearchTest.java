package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base
{
	public SearchTest()
	{
		super();
	}
	
	public WebDriver driver; //Recuerda hacer TODOS los WebDrivers public para que '(TakesScreenshot)driver' pueda acceder al driver.
	HomePage homePage;
	SearchPage searchPage;
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplicationURL();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct()
	{
		homePage = new HomePage(driver);
		searchPage = homePage.navigateToSearchPage(dataProp.getProperty("validProduct"));
		
		Assert.assertTrue(searchPage.retrieveStatusValidProduct());
	}
	
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct()
	{
		homePage = new HomePage(driver);
		searchPage = homePage.navigateToSearchPage(dataProp.getProperty("invalidProduct"));
		
		Assert.assertTrue(searchPage.retrieveNoProductMessage().contains("abc")); //ERROR
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		homePage = new HomePage(driver);
		searchPage = homePage.clickOnSearchButton();
		
		Assert.assertTrue(searchPage.retrieveNoProductMessage().contains(dataProp.getProperty("noProductTextMessage")));
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}