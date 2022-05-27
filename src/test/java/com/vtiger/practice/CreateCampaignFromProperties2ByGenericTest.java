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

public class CreateCampaignFromProperties2ByGenericTest {
	public static void main(String[] args) throws IOException {
		JavaClassUtility jutil=new JavaClassUtility();
		WebDriver driver=null;
		 
		//fetch the data by using key
		FileClassUtility.openPropertyFile(InterfaceCPathUtility.PROPERTYFILEPATH);
		
		ExcelCommonClassUtility.openExcel(InterfaceCPathUtility.EXCELFILEPATH);
		
		
		String url=FileClassUtility.getDataFromPropertyFile("url");
		String username=FileClassUtility.getDataFromPropertyFile("username");
		String password=FileClassUtility.getDataFromPropertyFile("password");
		String browser=FileClassUtility.getDataFromPropertyFile("browser");
		String timeout=FileClassUtility.getDataFromPropertyFile("timeout");
		
		long longTimeout=jutil.stringToLong(timeout);
		
		int randomnumber=jutil.getRandomNumber(1000);
		
//		FileInputStream fisExcel=new FileInputStream("./src/test/resources/excel.xlsx");
//		Workbook wb=WorkbookFactory.create(fisExcel);
		
		String campaignname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
	
//		Workbook wb=WorkbookFactory.create(fisExcel);
//		wb.getSheet("Contacts").getRow(15).createCell(5).setCellValue("Home page is Displayed");
//		wb.getSheet("Contacts").getRow(15).createCell(6).setCellValue("pass");

		
		
//		String campaignname=wb.getSheet("Contacts").getRow(8).getCell(1).getStringCellValue()+randomnumber;
		
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
//		Random r=new Random();
//		int num=r.nextInt(1000);
//	String campaignactual="Testyantra";
		
//		WebDriverManager.chromedriver().setup();
//		driver=new ChromeDriver();
//		driver.get(url);
//		driver.manage().window().maximize();
//		driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);

		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeout, driver);
		
		driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();
		
		WebElement moreLink=driver.findElement(By.xpath("//a[text()='More']"));
//		Actions act=new Actions(driver);
//		act.moveToElement(more).click().perform();
		WebDriverUtility wbDriverUtility=new WebDriverUtility();
		wbDriverUtility.mouseOverOnTheElement(moreLink, driver);
		
		driver.findElement(By.name("Campaigns")).click();
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
		
		driver.findElement(By.xpath("//input[@name='campaignname']")).sendKeys(campaignname);
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		
		
		String campaignameexpected=driver.findElement(By.xpath("//span[@id='dtlview_Campaign Name']")).getText();
		jutil.printStatement(campaignameexpected);
		
		if(campaignname.equals(campaignameexpected))
		{
			jutil.printStatement("Campaign created Successfully");
			jutil.printStatement("TestCase pass");
		}
		else
		{
			jutil.printStatement("TestCase Failed");
		}
		
//		WebElement adminstator=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
////		Actions act1=new Actions(driver);
////		act1.moveToElement(adminstator).click().perform();
//		WebDriverUtility.mouseOverOnTheElement(adminstator, driver);
//		
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//		
////		FileOutputStream fos=new FileOutputStream("./src/test/resources/excel.xlsx");
////		wb.write(fos);
//		ExcelCommonClassUtility.setDataInExcel("Contacts", 17 ,5,"create contact page displayed");
//		ExcelCommonClassUtility.setDataInExcel("Contacts", 17 ,6,"pass");
//		ExcelCommonClassUtility.writeExcel(InterfaceCPathUtility.EXCELWRITEPATH);
////		driver.quit();
//		WebDriverUtility.quitBrowser(driver);
//		
}
}
