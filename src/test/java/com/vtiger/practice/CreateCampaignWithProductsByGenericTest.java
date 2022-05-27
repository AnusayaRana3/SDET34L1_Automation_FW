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

public class CreateCampaignWithProductsByGenericTest {
	public static void main(String[] args) throws IOException {
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
		
		int randomnumber=jutil.getRandomNumber(1000);
		
//	FileInputStream fis1=new FileInputStream("./src/test/resources/excel.xlsx");
//		Workbook wb=WorkbookFactory.create(fis1);
//		Sheet sh=wb.getSheet("Contacts");
//		Row row=sh.getRow(8);
//		Cell cell=row.getCell(1);
//		String data=cell.getStringCellValue()+randomnumber;
//		String actual=data;
//		System.out.println(data);
//		
//		Row row1=sh.getRow(8);
//		Cell cell1=row1.getCell(2);
//		String data1=cell1.getStringCellValue()+randomnumber;
//		String actual1=data1;
//		System.out.println(data1);
		
		String productname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 2)+randomnumber;
		System.out.println(productname);
		String campaignname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
		System.out.println(campaignname);
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

//			
//			wb.close();
			
//			switch(browser) {
//			case "chrome":
//				WebDriverManager.chromedriver().setup();
//				driver=new ChromeDriver();
//			break;
//			case "Edge":
//				WebDriverManager.edgedriver().setup();
//				driver=new EdgeDriver();
//			break;
//			case "firefox":
//				WebDriverManager.firefoxdriver().setup();
//				driver=new FirefoxDriver();
//			break;
//			default:
//				System.out.println("please specify proper browser key");
//				WebDriverManager.chromedriver().setup();
//				driver=new ChromeDriver();
//				break;
//		}
//		WebDriverManager.chromedriver().setup();
//			driver=new ChromeDriver();
//			driver.get(url);
//			driver.manage().window().maximize();
//			driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);

			WebDriverUtility.navigateApp(url, driver);
			WebDriverUtility.browserSetting(longTimeout, driver);
			WebDriverUtility.explicitlywait(driver, longTimeout);
			
			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			driver.findElement(By.xpath("//a[text()='Products']")).click();
			driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
			
			driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(productname);
			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			
			WebDriverUtility.waitUntilElementClickable(driver.findElement(By.linkText("More")));
		
			WebElement moreLink=driver.findElement(By.linkText("More"));
			webDriverUtility.mouseOverOnTheElement(moreLink, driver);
			
			driver.findElement(By.name("Campaigns")).click();
			driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();
			
			driver.findElement(By.name("campaignname")).sendKeys(campaignname);
			
//			driver.findElement(By.id("dtlview_Product Name")).getText();
			
//			String currentid=driver.getWindowHandle();
//			Set<String> id=driver.getWindowHandles();
//			for(String allid:id)
//			{
//				if(!allid.equals(currentid))
//				{
//					driver.switchTo().window(allid);	
//				}
//			}
		
			driver.findElement(By.xpath("//img[@title='Select']")).click();
			WebDriverUtility.switchToWindowBasedOnTitle(driver,"Products");
			
			driver.findElement(By.id("search_txt")).sendKeys(productname);
			driver.findElement(By.name("search")).click();
			
			driver.findElement(By.xpath("//a[.='"+productname+"']")).click();
			
//		    expected=expected.trim();
			
			WebDriverUtility.switchToWindowBasedOnTitle(driver,"Campaigns");
			
			
			
		driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			
						
//			String expected1=driver.findElement(By.id("dtlview_Campaign Name")).getText();
		WebElement actualCampaignName=driver.findElement(By.id("dtlview_Campaign Name"));
		WebElement actualProductName=driver.findElement(By.xpath("//span[@id='dtlview_Product']/a"));
//		
//		if((productname.equals(expected))&&(productname.equals(expected1)))
//		{
//			jutil.printStatement("Test case is passed");
//			jutil.printStatement("Campaign created with product");
//		}
//		else
//		{
//			jutil.printStatement("Campaign not created with product");
//			jutil.printStatement("Test case is Failed");
//		}
		
			jutil.assertionThroughIfCondition(actualCampaignName.getText(), campaignname,"campaign with product");
			jutil.assertionThroughIfCondition(actualProductName.getText(), productname,"correct product name");
			
			WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
	//		Actions act1=new Actions(driver);
			webDriverUtility.mouseOverOnTheElement(img, driver);
			WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
			webDriverUtility.mouseOverOnTheElement(logout, driver);
			
			WebDriverUtility.quitBrowser(driver);
}
}
