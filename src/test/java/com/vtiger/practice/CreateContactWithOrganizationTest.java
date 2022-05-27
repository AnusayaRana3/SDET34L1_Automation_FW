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

public class CreateContactWithOrganizationTest {
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
			//String browser=property.getProperty("browser");
			String timeout=property.getProperty("timeout");
			
			
			
			long longTimeout=Long.parseLong(timeout);
			WebDriver driver=null;
			
			FileInputStream fisexcel=new FileInputStream("./src/test/resources/excel.xlsx");
			Workbook wb=WorkbookFactory.create(fisexcel);
			
			Random r=new Random();
			int num=r.nextInt(1000);
			
			Sheet sh=wb.getSheet("Contacts");
			Row row=sh.getRow(5);
			Cell cell=row.getCell(1);
			String organizationName=cell.getStringCellValue()+num;
			String actual=organizationName;
			System.out.println(organizationName);
			
			Row row1=sh.getRow(5);
			Cell cell1=row1.getCell(2);
			String lastName=cell1.getStringCellValue()+num;
			String actual1=lastName;
			System.out.println(lastName);
			//wb.close();
			
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
			
			
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.cssSelector("[title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(organizationName);
			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			String expected=driver.findElement(By.id("dtlview_Organization Name")).getText();
			if(actual.equals(expected))
			{
				System.out.println("Test case is passed");
				System.out.println("organization created");
			}
			else
			{
				System.out.println("organization not created");
				System.out.println("Test case is Failed");
			}
			driver.findElement(By.linkText("Contacts")).click();
			driver.findElement(By.cssSelector("[title='Create Contact...']")).click();
			driver.findElement(By.name("lastname")).sendKeys(actual1);
			driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td/img")).click();
			//String currentid=driver.getWindowHandle();
			Set<String> allid=driver.getWindowHandles();
			for(String id:allid)
			{
				driver.switchTo().window(id);	
				if(driver.getTitle().contains("Accounts&action"))
				{
					break;
				}
			}
			driver.findElement(By.id("search_txt")).sendKeys(actual);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[contains(@onclick,'set_return_contact_address')]")).click();
			
			Set<String> allid1=driver.getWindowHandles();
			for(String id1:allid1)
			{
				driver.switchTo().window(id1);
				if(driver.getTitle().contains("Contacts&action"))
				{
					break;
				}

			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			String expected1=driver.findElement(By.id("dtlview_Last Name")).getText();
			if((actual.equals(expected))&&(actual1.equals(expected1)))
			{
				System.out.println("Test case is passed");
				System.out.println("Contact created with organization");
			}
			else
			{
				System.out.println("Contact created with organization");
				System.out.println("Test case is Failed");
			}
			Actions act1=new Actions(driver);
			WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
			act1.moveToElement(img).perform();
			WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
			act1.moveToElement(logout).click().perform();
			
	}
	}
}
