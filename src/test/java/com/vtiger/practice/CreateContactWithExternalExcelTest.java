package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithExternalExcelTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		//create object for properties class
			Properties property=new Properties();
			//load all keys
			property.load(fis);
			//fetch the data by using key
			String url=property.getProperty("url");
			String username=property.getProperty("username");
			String password=property.getProperty("password");
			String browser=property.getProperty("browser");
			String timeout=property.getProperty("timeout");
			
			
			
			long longTimeout=Long.parseLong(timeout);
		//Excel File	
			FileInputStream fisexcel=new FileInputStream("./src/test/resources/excel.xlsx");
			Workbook wb=WorkbookFactory.create(fisexcel);
			Sheet sh=wb.getSheet("Contacts");
			Row row=sh.getRow(5);
			Cell cell=row.getCell(1);
			
			Random r=new Random();
			int num=r.nextInt(1000);
			String value=cell.getStringCellValue()+num;
			//String actual=value;
			System.out.println(value);
			
//			Row row1=sh.getRow(5);
//			Cell cell1=row1.getCell(2);
//			String lastName=cell1.getStringCellValue()+num;
//			String actual1=lastName;
//			System.out.println(lastName);
//			
		//WebDrivermanager.chromedriver().setup();
		//WebDriver driver=new ChromeDriver();	
			WebDriver driver=null;
			if(browser.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
				driver=new ChromeDriver();	
			}
			else if(browser.equalsIgnoreCase("edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver=new EdgeDriver();
			}
			else if(browser.equalsIgnoreCase("firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver=new FirefoxDriver();
			}
			
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
			
		//TestCase Step 1---> Login to the application
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			if(driver.getTitle().contains("Home"))
			{
				wb.getSheet("Contacts").getRow(15).createCell(5).setCellValue("Home page is Displayed");
				wb.getSheet("Contacts").getRow(15).createCell(6).setCellValue("pass");
			}
		//TestCase Step 2---> Click on Contacts	
			driver.findElement(By.linkText("Contacts")).click();
			
			if(driver.getTitle().contains("Contacts"))
			{
				wb.getSheet("Contacts").getRow(16).createCell(5).setCellValue("Contacts page is Displayed");
				wb.getSheet("Contacts").getRow(16).createCell(6).setCellValue("pass");
			}
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(value);
		    
		    driver.findElement(By.className("crmButton")).click();
		    WebElement actualLastName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		    
		    if(actualLastName.getText().equalsIgnoreCase(value))
		    {
		    	System.out.println("Contacts created successfully");
		    	System.out.println("TestCase pass");
		    }
		    Actions act1=new Actions(driver);
			WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			act1.moveToElement(img).perform();
			WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
			act1.moveToElement(logout).click().perform();
		//Write the Data 
			FileOutputStream fos=new FileOutputStream("./src/test/resources/excel.xlsx");
			wb.write(fos);
			wb.close();
			driver.quit();
}
}
