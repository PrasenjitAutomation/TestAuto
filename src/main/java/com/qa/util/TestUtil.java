package com.qa.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.qa.base.TestBase;

public class TestUtil extends TestBase{

	static Workbook book;
	static Sheet sheet;
	public static Object[][] getTestData(String sheetName) throws Exception {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+ "/src/main/java/com/qa/testdata/FreeCrmTestData.xlsx");
		book = WorkbookFactory.create(file);
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
				for (int i = 0; i < sheet.getLastRowNum(); i++) {
			      for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				    data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				}
		}
		return data;
	}
	
	 public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
     }
}