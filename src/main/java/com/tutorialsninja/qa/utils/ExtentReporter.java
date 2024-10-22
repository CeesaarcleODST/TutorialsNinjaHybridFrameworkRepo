package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter
{
	public static ExtentReports generateExtentReporter()
	{
		ExtentReports extentReports = new ExtentReports();
		File extentReportFile = new File(System.getProperty("user.dir") + "/test-output/ExtentReporter/extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Tutorials Ninja Test Suite");
		sparkReporter.config().setDocumentTitle("TN Extent Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		Properties prop = new Properties();
		File propFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/config/config.properties");
		
		try {
			FileInputStream fis = new FileInputStream(propFile);
			prop.load(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}

		extentReports.setSystemInfo("Application URL", prop.getProperty("url"));
		extentReports.setSystemInfo("Browser Name", prop.getProperty("browserName"));
		extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
		extentReports.setSystemInfo("Username", System.getProperty("user.name"));
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("Email Address", prop.getProperty("validEmail"));
		extentReports.setSystemInfo("Password", prop.getProperty("validPassword"));
		
		extentReports.attachReporter(sparkReporter);
		
		return extentReports;
	}
}