package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.FileClassUtility;
import com.sdet34l1.genericUtility.InterfaceCPathUtility;
import com.sdet34l1.genericUtility.JavaClassUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithExternalExcelByGenericTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		JavaClassUtility jutil=new JavaClassUtility();
		WebDriver driver=null;
		WebDriverUtility webDriverUtility=new WebDriverUtility();
		
		ExcelCommonClassUtility.openExcel(InterfaceCPathUtility.EXCELFILEPATH);
		//fetch the data by using key
		FileClassUtility.openPropertyFile(InterfaceCPathUtility.PROPERTYFILEPATH);
		
		String url=FileClassUtility.getDataFromPropertyFile("url");
		String username=FileClassUtility.getDataFromPropertyFile("username");
		String password=FileClassUtility.getDataFromPropertyFile("password");
		String browser=FileClassUtility.getDataFromPropertyFile("browser");
		String timeout=FileClassUtility.getDataFromPropertyFile("timeout");
		
		long longTimeout=jutil.stringToLong(timeout);
		
		int randomnumber=jutil.getRandomNumber(1000);
		
		//Excel File	
//			FileInputStream fisexcel=new FileInputStream("./src/test/resources/excel.xlsx");
//			Workbook wb=WorkbookFactory.create(fisexcel);
//			Sheet sh=wb.getSheet("Contacts");
//			Row row=sh.getRow(5);
//			Cell cell=row.getCell(1);
//			
//			String value=cell.getStringCellValue()+randomnumber;
//			//String actual=value;
		
		String value=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 1)+randomnumber;
		jutil.printStatement(value);
			
//			Row row1=sh.getRow(5);
//			Cell cell1=row1.getCell(2);
//			String lastName=cell1.getStringCellValue()+num;
//			String actual1=lastName;
//			System.out.println(lastName);
//			
		//WebDrivermanager.chromedriver().setup();
		//WebDriver driver=new ChromeDriver();	
		//	WebDriver driver=null;
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
			
//			driver.get(url);
//			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
			
			WebDriverUtility.navigateApp(url, driver);
			WebDriverUtility.browserSetting(longTimeout, driver);
			
		//TestCase Step 1---> Login to the application
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			if(driver.getTitle().contains("Home"))
			{
//				wb.getSheet("Contacts").getRow(15).createCell(5).setCellValue("Home page is Displayed");
//				wb.getSheet("Contacts").getRow(15).createCell(6).setCellValue("pass");
				
				ExcelCommonClassUtility.setDataInExcel("Contacts", 15 ,5,"Home page should displayed");
				ExcelCommonClassUtility.setDataInExcel("Contacts", 15 ,6," TestCase pass");
				
			}
			
		//TestCase Step 2---> Click on Contacts	
			driver.findElement(By.linkText("Contacts")).click();
			
			if(driver.getTitle().contains("Contacts"))
			{
//				wb.getSheet("Contacts").getRow(16).createCell(5).setCellValue("Contacts page is Displayed");
//				wb.getSheet("Contacts").getRow(16).createCell(6).setCellValue("pass");
				
				ExcelCommonClassUtility.setDataInExcel("Contacts", 16 ,5,"Contacts page should displayed");
				ExcelCommonClassUtility.setDataInExcel("Contacts", 16 ,6," TestCase pass");
				
			}
			
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		    driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(value);
		    
		    driver.findElement(By.className("crmButton")).click();
		    
		    WebElement actualLastName=driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));
		    
		    if(actualLastName.getText().equalsIgnoreCase(value))
		    {
		    	jutil.printStatement("Contacts created successfully");
		    	jutil.printStatement("TestCase pass");
		    }
		  
			WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//			Actions act1=new Actions(driver);
//			act1.moveToElement(img).perform();
			webDriverUtility.mouseOverOnTheElement(img, driver);
			WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
//			act1.moveToElement(logout).click().perform();
			webDriverUtility.mouseOverOnTheElement(logout, driver);
	
		//Write the Data 
//			FileOutputStream fos=new FileOutputStream("./src/test/resources/excel.xlsx");
//			wb.write(fos);
			
			ExcelCommonClassUtility.writeExcel(InterfaceCPathUtility.EXCELWRITEPATH);
		
//			wb.close();
			ExcelCommonClassUtility.closeExcel();
//			driver.quit();
			WebDriverUtility.quitBrowser(driver);
}
}
