package com.vtiger.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentWithIframTest {
	public static void main(String[] args) throws AWTException, IOException {
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
		
		//Excel File	
		FileInputStream fisexcel=new FileInputStream("./src/test/resources/excel.xlsx");
		Workbook wb=WorkbookFactory.create(fisexcel);
		
		Random r=new Random();
		int num=r.nextInt(1000);
		String Title=wb.getSheet("Documents").getRow(2).getCell(1).getStringCellValue()+num;
		String documentpath=wb.getSheet("Documents").getRow(2).getCell(2).getStringCellValue();
		String documentDescription=wb.getSheet("Documents").getRow(2).getCell(3).getStringCellValue();
		
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
	
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
	driver.get(url);
	
	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[text()='Documents']")).click();
	driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
	driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(Title);
	
	WebElement iframe=driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
	driver.switchTo().frame(iframe);
	
	driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(documentDescription);
	
	Robot robot=new Robot();
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_A);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyRelease(KeyEvent.VK_A);
	
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//span[@class='cke_button']//a[@id='cke_5']")).click();
	driver.findElement(By.xpath("//span[@class='cke_button']//a[@id='cke_6']")).click();
	
	WebElement filechoose=driver.findElement(By.xpath("//input[@id='filename_I__']"));
	filechoose.sendKeys(documentpath);
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//validate
	String oldtitle=driver.findElement(By.xpath("//span[@id='dtlview_Title']")).getText();
	if(Title.equals(oldtitle))
	{
	System.out.println("title is:" +oldtitle);	
    }
	
	//data pass in excel
	if(driver.getTitle().contains("Documents"))
	{
		wb.getSheet("Documents").getRow(2).createCell(4).setCellValue("Documents page is Displayed");
		wb.getSheet("Documents").getRow(2).createCell(5).setCellValue("pass");
	}

	//Logout
	Actions act1=new Actions(driver);
	WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	act1.moveToElement(img).perform();
	WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
	act1.moveToElement(logout).click().perform();
	
	//Write the Data 
	FileOutputStream fos=new FileOutputStream("./src/test/resources/excel.xlsx");
	wb.write(fos);
	wb.close();
	driver.quit();
}
}
