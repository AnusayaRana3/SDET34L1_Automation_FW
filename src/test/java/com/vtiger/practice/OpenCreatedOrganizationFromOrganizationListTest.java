package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
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

public class OpenCreatedOrganizationFromOrganizationListTest {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
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
			WebDriverUtility.navigateApp(url, driver);
			WebDriverUtility.browserSetting(longTimeout, driver);
			
		
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
	
	//Navigate to Organizations
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(organizationname);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
String expected=driver.findElement(By.id("dtlview_Organization Name")).getText();
		
		if(organizationname.equals(expected))
		{
			jutil.printStatement("Correct Organization Name");	
		}
		else
		{
			jutil.printStatement("Incorrect Organization Name");

		}
		
driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		
		driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(organizationname);
		WebElement dropdown=driver.findElement(By.xpath("//select[@id='bas_searchfield']"));
		WebDriverUtility.initializeSelectClass(dropdown);
		WebDriverUtility.dropDownHandleByValue("accountname", dropdown);
		driver.findElement(By.xpath(" //input[@value=' Search Now '][1]")).click();
		
		driver.findElement(By.xpath("//a[@title='Organizations']")).click();
		
		String information=driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]")).getText();
		System.out.println(information);
		
		WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//		Actions act1=new Actions(driver);
//		act1.moveToElement(img).perform();
		webDriverUtility.mouseOverOnTheElement(img, driver);
		
		WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
//		act1.moveToElement(logout).click().perform();
		webDriverUtility.mouseOverOnTheElement(logout, driver);
		
//		WebDriverUtility.quitBrowser(driver);
}
}
