package DW;

import org.testng.annotations.Test;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class DW002 {
	AndroidDriver driver;
	@Test
	public void CheckLogInLogOut() throws IOException {

		String path = "C:\\Users\\AnkurSaxena\\Desktop\\SDET\\test.xls";
		FileInputStream fis = new FileInputStream(path);
		Workbook workbook = new HSSFWorkbook(fis);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);
		int lastRow = sheet.getLastRowNum();
		String[] value = new String[3];
		for(int i=1; i<=lastRow; i++){
			int a=0;
			Row row = sheet.getRow(i);
			int lastCell = row.getLastCellNum();
			for(int j=0; j<lastCell; j++){

				org.apache.poi.ss.usermodel.Cell cell = row.getCell(j);
				value[j] = cell.getStringCellValue();
				a=a+1;
			}
			driver.findElement(MobileBy.xpath("//input[@name=\"Email\"]")).sendKeys(value[0]);
			driver.findElement(MobileBy.xpath("//input[@name=\"Password\"]")).sendKeys(value[1]);
			driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
			driver.hideKeyboard();
			driver.findElement(MobileBy.xpath("//input[@value='Log in']")).sendKeys(Keys.ENTER);
			String ExpectedText=value[0];
			String ActualText=driver.findElement(MobileBy.linkText(value[0])).getText();
			Assert.assertEquals(ExpectedText, ActualText);	
			


			driver.findElement(MobileBy.xpath("//a[text()='Log out']")).click();
			ExpectedText="Log in";
			ActualText=driver.findElement(MobileBy.xpath("//a[text()='Log in']")).getText();
			Assert.assertEquals(ExpectedText, ActualText);
			driver.findElement(MobileBy.xpath("//a[text()='Log in']")).click();
		}


	}
	@BeforeClass
	public void beforeClass() throws MalformedURLException {
		DesiredCapabilities capability=new DesiredCapabilities();
		capability.setCapability("deviceName","Ankur");
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
		capability.setCapability(MobileCapabilityType.BROWSER_NAME,"Chrome");
		driver=new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),capability);
		driver.get("http://demowebshop.tricentis.com/login");
	}

	@AfterClass
	public void afterClass() {

		driver.close();
	}

}
