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

public class CreateProductsFromPropertiesTest {
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
		String productactual="coffee";
		productactual=productactual+num;
		
		WebDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
		
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
		System.out.println("Correct product Name");	
		}
		else
		{
			System.out.println("Incorrectproduct Name");
		}
		Actions act1=new Actions(driver);
		WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act1.moveToElement(img).perform();
		WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
		act1.moveToElement(logout).click().perform();	
}
}
