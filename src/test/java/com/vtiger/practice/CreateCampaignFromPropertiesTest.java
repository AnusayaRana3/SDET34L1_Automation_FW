package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignFromPropertiesTest {
	public static void main(String[] args) throws IOException {
		//convert the physical file into java readable object
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
		WebDriver driver=null;
		 
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
		Random r=new Random();
		int num=r.nextInt(1000);
		String campaignactual="Testyantra";
		campaignactual=campaignactual+num;
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		WebElement more=driver.findElement(By.xpath("//a[text()='More']"));
		Actions act=new Actions(driver);
		act.moveToElement(more).click().perform();
		
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(campaignactual);
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();
		
		
		String campaignameexpected=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		System.out.println(campaignameexpected);
		if(campaignactual.equals(campaignameexpected))
		{
			System.out.println("TestCase pass");
		}
		else
		{
			System.out.println("TestCase Failed");
		}
		
		WebElement adminstator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act1=new Actions(driver);
		act1.moveToElement(adminstator).click().perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
	}
}
