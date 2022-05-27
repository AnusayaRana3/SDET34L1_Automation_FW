package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.FileClassUtility;
import com.sdet34l1.genericUtility.InterfaceCPathUtility;
import com.sdet34l1.genericUtility.JavaClassUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationByGenericTest {
public static void main(String[] args) throws InterruptedException, IOException {
		
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
		
		int randomnumber =jutil.getRandomNumber(1000);
		
		String organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
		
		switch(browser) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		break;
		case "Edge":
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		break;
		default:
			System.out.println("please specify proper browser key");
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
			break;
	}

		
//		String actual="Testyantra"+randomnumber;
//		WebDriverManager.chromedriver().setup();
//		 driver=new ChromeDriver();
		 
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
//		driver.get(url);
		 	WebDriverUtility.navigateApp(url, driver);
			WebDriverUtility.browserSetting(longTimeout, driver);
			
		
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organizationname);
		
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		String expected=driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(organizationname.equals(expected))
		{
			jutil.printStatement("Correct Organization Name");	
		}
		else
		{
			jutil.printStatement("Incorrect Organization Name");
			//System.out.println(expected);
		}
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@title='Duplicate [Alt+U]'])[1]")));
		
		WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions act1=new Actions(driver);
//		act1.moveToElement(img).perform();
		webDriverUtility.mouseOverOnTheElement(img, driver);
		
		WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
//		act1.moveToElement(logout).click().perform();
		webDriverUtility.mouseOverOnTheElement(logout, driver);
		
		WebDriverUtility.quitBrowser(driver);
	}
}
