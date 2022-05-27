package com.vtiger.practice;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInDatabaseWRTGUI {
public static void main(String[] args) {
	Random r=new Random();
	int num=r.nextInt(1000);

	WebDriverManager.chromedriver().setup();
	WebDriver driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
	driver.get("http://localhost:8084/");
	driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
	driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
	driver.findElement(By.xpath("//button[text()='Sign in']")).click();
	driver.findElement(By.linkText("Projects")).click();
	driver.findElement(By.xpath("//span[text()='Create Project']")).click();
	String projectName="SDET34L1"+num;
	driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(projectName);
	//JavascriptExecutor jse=(JavascriptExecutor)driver;
	//jse.executeScript("document.getElementByname('Team Size').value='7'");
	driver.findElement(By.name("createdBy")).sendKeys("Anusaya");
	WebElement projectstatusdropdown=driver.findElement(By.name("status"));
	Select select=new Select(projectstatusdropdown);
	select.selectByVisibleText("On Goging");
	driver.findElement(By.xpath("//input[@value='Add project']")).click();
}
}
