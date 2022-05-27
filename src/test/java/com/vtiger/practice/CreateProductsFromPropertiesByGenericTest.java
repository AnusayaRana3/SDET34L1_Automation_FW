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

public class CreateProductsFromPropertiesByGenericTest {
public static void main(String[] args) throws IOException {
		
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
		
//		String productactual="coffee";
//		productactual=productactual+randomnumber;
		String productactual=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 2)+randomnumber;
		
//		WebDriverManager.chromedriver().setup();
//		driver=new ChromeDriver();
//		
//		driver.get(url);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
		
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeout, driver);
		
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//a[text()='Products']")).click();
		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(productactual);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		String productexpected=driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		
		if(productactual.equals(productexpected))
		{
			jutil.printStatement("Correct product Name");	
		}
		else
		{
			jutil.printStatement("Incorrectproduct Name");
		}
		
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
