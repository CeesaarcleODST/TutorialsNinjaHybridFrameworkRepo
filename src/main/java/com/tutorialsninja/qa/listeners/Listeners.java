package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utils.ExtentReporter;
import com.tutorialsninja.qa.utils.Utilities;

public class Listeners implements ITestListener
{
	ExtentReports extentReports;
	ExtentTest extentTest;
	
	@Override
	public void onStart(ITestContext context)
	{
		extentReports = ExtentReporter.generateExtentReporter();
	}
	
	@Override
	public void onTestStart(ITestResult result)
	{
		extentTest = extentReports.createTest(result.getName());
		extentTest.log(Status.INFO, result.getName() + " has started.");
	}

	@Override
	public void onTestSuccess(ITestResult result)
	{
		extentTest.log(Status.PASS, result.getName() + " has successfully passed.");
	}

	@Override
	public void onTestFailure(ITestResult result)
	{
		WebDriver driver = null;
		
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} //Así se obtiene 'driver'.
		
		String destinationScreenshotPath = Utilities.captureScreenshot(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath); //Adjuntas (attach) el screenshot al reporte.
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, result.getName() + " got failed.");
	}

	@Override
	public void onTestSkipped(ITestResult result)
	{
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, result.getName() + " has been skipped.");
	}

	@Override
	public void onFinish(ITestContext context)
	{
		extentReports.flush();
		
		String pathOfExtentReport = System.getProperty("user.dir") + "/test-output/ExtentReporter/extentReport.html";
		File extentReportFile = new File(pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReportFile.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}