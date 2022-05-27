package com.rmgyantra.projectTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;
import com.sdet34l1.genericUtility.FileClassUtility;
import com.sdet34l1.genericUtility.InterfaceCPathUtility;
import com.sdet34l1.genericUtility.JavaClassUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ValidateProjectNameInDataBaseWrtGUITest {
	public static void main(String[] args) throws SQLException, EncryptedDocumentException, IOException {
JavaClassUtility jutil=new JavaClassUtility();
		
		//fetch the data by using key
		FileClassUtility.openPropertyFile(InterfaceCPathUtility.PROPERTYFILEPATH);
		String url=FileClassUtility.getDataFromPropertyFile("url");
		String username=FileClassUtility.getDataFromPropertyFile("username");
		String password=FileClassUtility.getDataFromPropertyFile("password");
		String browser=FileClassUtility.getDataFromPropertyFile("browser");
		String timeout=FileClassUtility.getDataFromPropertyFile("timeout");
		
		long longTimeout=jutil.stringToLong(timeout);
		
		int randomnumber=jutil.getRandomNumber(1000);
		
		FileInputStream fisExcel=new FileInputStream("./src/test/resources/excel.xlsx");
		Workbook wb=WorkbookFactory.create(fisExcel);
	
		String actual="Sdet34"+randomnumber;
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(longTimeout, TimeUnit.SECONDS);
		driver.get(url);
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra",Keys.TAB,"rmgy@9999",Keys.ENTER);
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.className("btn-success")).click();
		driver.findElement(By.name("projectName")).sendKeys(actual);
		driver.findElement(By.name("createdBy")).sendKeys("Anu");
		WebElement dropdown=driver.findElement(By.xpath("(//select[@class='form-control'])[2]"));
		Select s=new Select(dropdown);
		s.selectByVisibleText("On Goging");
		driver.findElement(By.cssSelector("[value='Add Project']")).click();
		driver.findElement(By.className("table-hover")).getText();
		Connection connection=null;
		Driver driver1=new Driver();
		DriverManager.registerDriver(driver1);
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		Statement statement=connection.createStatement();
		ResultSet result=statement.executeQuery("select * from project");
		while(result.next())
		{
			String s1=(result.getString(4));
			if(s1.equals(actual))
			{
			System.out.println("passed :"+actual);
			}	
		}
		connection.close();
		wb.close();
		driver.quit();
		}
		}



