package com.tutorialsninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities
{
	public static final int IMPLICIT_WAIT_TIME = 5;
	public static final int PAGE_LOAD_TIME = 5;
	
	public static String generateEmailWithTimeStamp()
	{
		Date date = new Date();
		return "ceesaarcleodst" + date.toString().replace(":", "_").replace(" ", "_") + "@gmail.com";
	}
	
	public static Object[][] getTestDataFromExcelFile(String sheetName)
	{
		File excelFile = new File(System.getProperty("user.dir") + "/src/main/java/com/tutorialsninja/qa/testdata/tutorialsNinjaExcel.xlsx");
		XSSFWorkbook workbook = null;
		
		try {
			FileInputStream fis3 = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fis3);
		}catch(Throwable g) {
			g.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		int rows = sheet.getLastRowNum();
		int columns = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][columns];
		
		for(int i = 0; i < rows; i++)
		{
			XSSFRow row = sheet.getRow(i+1);
			for(int j = 0; j < columns; j++)
			{
				XSSFCell cell = row.getCell(j);
				CellType cellType = cell.getCellType();
				switch(cellType)
				{
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				default:
					break;
				}
			}
		}
		return data;
	}
	
	public static String captureScreenshot(WebDriver driver, String testName)
	{
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir") + "/Screenshots/" + testName + ".png";
		
		try {
			FileHandler.copy(srcScreenshot, new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		} //Así se guarda el screenshot en la carpeta que creaste  "Screenshots".
		
		return destinationScreenshotPath;
	}
}