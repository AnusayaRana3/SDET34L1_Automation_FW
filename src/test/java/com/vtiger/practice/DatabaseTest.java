package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatabaseTest {
	public static void main(String[] args) throws SQLException {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/vtiger","root","root");
		Statement statement=connection.createStatement();
		ResultSet result=statement.executeQuery("Select * from Contacts");
		while(result.next())
		{
			WebDriverManager.chromedriver().setup();
			WebDriver driver1=new ChromeDriver();
			driver1.get("http://localhost:8888");
			driver1.manage().window().maximize();
			driver1.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver1.findElement(By.name("user_name")).sendKeys(result.getString(3));
			driver1.findElement(By.name("user_password")).sendKeys(result.getString(4));
			driver1.findElement(By.id("submitButton")).click();
			driver1.findElement(By.xpath("//a[text()='Contacts']")).click();
			driver1.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		
			
		connection.close();
		driver1.findElement(By.xpath("lastname")).sendKeys("Rout");
		driver1.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		driver1.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	}
}
