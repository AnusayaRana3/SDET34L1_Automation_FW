package com.vtiger.practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationInIndustryTest {
	public static void main(String[] args) {
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
		driver.findElement(By.id("submitButton")).click();
		
		driver.findElement(By.xpath("//td[@class='tabUnSelected']/a[text()='Organizations']")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.xpath("//input[@name='accountname']")).sendKeys(actual);
		
		WebElement dropdown=driver.findElement(By.xpath("//select[@name='industry']"));
		Select s=new Select(dropdown);
		s.selectByValue("Education");
		
		WebElement dropdown1=driver.findElement(By.xpath("//select[@name='accounttype']"));
		Select s1=new Select(dropdown1);
		s1.selectByValue("Press");
		
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
		String expected=driver.findElement(By.id("dtlview_Organization Name")).getText();
		if(actual.equals(expected))
		{
		System.out.println("Correct Organization Name");	
		}
		else
		{
			System.out.println("Incorrect Organization Name");
		}
		String actual1="Education";
		String expected1=driver.findElement(By.xpath("//span[@id='dtlview_Industry']")).getText();
		if(actual1.equals(expected1))
		{
			System.out.println("Correct Selected industry");
		}
		String actual2="Press";
		String expected2=driver.findElement(By.xpath("//span[@id='dtlview_Type']")).getText();
		if(actual2.equals(expected2))
		{
			System.out.println("Correct Selected Type");
		}
		
		Actions act1=new Actions(driver);
		WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		act1.moveToElement(img).perform();
		WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
		act1.moveToElement(logout).click().perform();	
	}
}
