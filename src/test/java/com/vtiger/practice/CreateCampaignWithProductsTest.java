package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductsTest {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("./src/test/resources/commonData.properties");
		//create object for properties class
			Properties property=new Properties();
			//load all keys
			property.load(fis);
			//fetch the data by using key
			String url=property.getProperty("url");
			String username=property.getProperty("username");
			String password=property.getProperty("password");
			//String browser=property.getProperty("browser");
			String timeout=property.getProperty("timeout");
			
			Random r=new Random();
			int num=r.nextInt(1000);
			
			long longTimeout=Long.parseLong(timeout);
			WebDriver driver=null;
			
			FileInputStream fis1=new FileInputStream("./src/test/resources/excel.xlsx");
			Workbook wb=WorkbookFactory.create(fis1);
			Sheet sh=wb.getSheet("Contacts");
			Row row=sh.getRow(8);
			Cell cell=row.getCell(1);
			String data=cell.getStringCellValue()+num;
			String actual=data;
			System.out.println(data);
			
			Row row1=sh.getRow(8);
			Cell cell1=row1.getCell(2);
			String data1=cell1.getStringCellValue()+num;
			String actual1=data1;
			System.out.println(data1);
			wb.close();
			
//			switch(browser) {
//			case "chrome":
//				WebDriverManager.chromedriver().setup();
//				driver=new ChromeDriver();
//			break;
//			case "Edge":
//				WebDriverManager.edgedriver().setup();
//				driver=new EdgeDriver();
//			break;
//			case "firefox":
//				WebDriverManager.firefoxdriver().setup();
//				driver=new FirefoxDriver();
//			break;
//			default:
//				System.out.println("please specify proper browser key");
//				WebDriverManager.chromedriver().setup();
//				driver=new ChromeDriver();
//				break;
//		}
		WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
			
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			driver.findElement(By.linkText("Products")).click();
			driver.findElement(By.cssSelector("[alt='Create Product...']")).click();
			driver.findElement(By.name("productname")).sendKeys(actual);
			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			String expected=driver.findElement(By.id("dtlview_Product Name")).getText();
			expected=expected.trim();
			if(actual.equals(expected))
			{	
				System.out.println("Test case is passed");
				System.out.println("product created");
			}
			else
			{
				System.out.println(expected);
				System.out.println("Test case is Failed");
			}
			driver.findElement(By.xpath("//a[text()='More']")).click();
			driver.findElement(By.name("Campaigns")).click();
			driver.findElement(By.cssSelector("[alt='Create Campaign...']")).click();
			driver.findElement(By.name("campaignname")).sendKeys(actual1);
			driver.findElement(By.cssSelector("[alt='Select']")).click();
			String currentid=driver.getWindowHandle();
			Set<String> id=driver.getWindowHandles();
			for(String allid:id)
			{
				if(!allid.equals(currentid))
				{
					driver.switchTo().window(allid);	
				}
			}
			driver.findElement(By.id("search_txt")).sendKeys(actual1);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[contains(@onclick,'set_return_specific')]")).click();
			driver.switchTo().window(currentid);
			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			String expected1=driver.findElement(By.id("dtlview_Campaign Name")).getText();
			expected=expected.trim();
			if((actual.equals(expected))&&(actual1.equals(expected1)))
			{
				System.out.println("Test case is passed");
				System.out.println("Campaign created with product");
			}
			else
			{
				System.out.println("Campaign not created with product");
				System.out.println("Test case is Failed");
			}
			Actions act1=new Actions(driver);
			WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			act1.moveToElement(img).perform();
			WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
			act1.moveToElement(logout).click().perform();
			
}
}
