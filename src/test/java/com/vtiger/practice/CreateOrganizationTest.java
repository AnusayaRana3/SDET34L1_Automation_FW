package com.vtiger.practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationTest {
	public static void main(String[] args) throws InterruptedException {
		Random r=new Random();
		int num=r.nextInt(1000);
		String actual="Testyantra"+num;
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.get("http://localhost:8888/");
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys("root");
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();
		driver.findElement(By.xpath("//td[@align='center']/a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(actual);
		driver.findElement(By.xpath("//input[@class='crmbutton small save']")).click();
		
		String expected=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actual.equals(expected))
		{
		System.out.println("Correct Organization Name");	
		}
		else
		{
			System.out.println("Incorrect Organization Name");
			//System.out.println(expected);
		}
		WebDriverWait wait=new WebDriverWait(driver,10);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//input[@title='Duplicate [Alt+U]'])[1]")));
		Actions act1=new Actions(driver);
		WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act1.moveToElement(img).perform();
		WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
		act1.moveToElement(logout).click().perform();
		
	}
}
