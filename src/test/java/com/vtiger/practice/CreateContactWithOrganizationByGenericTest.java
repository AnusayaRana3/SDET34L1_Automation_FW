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

public class CreateContactWithOrganizationByGenericTest {
	public static void main(String[] args) throws IOException, InterruptedException {
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
		
//			FileInputStream fisexcel=new FileInputStream("./src/test/resources/excel.xlsx");
//			Workbook wb=WorkbookFactory.create(fisexcel);
			
	//String organizationName=wb.getSheet("Contacts").getRow(8).getCell(1).getStringCellValue()+randomnumber;
	//	String organizationName=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
		
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
			
			String organizationName=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 1)+randomnumber;
			
//			Sheet sh=wb.getSheet("Contacts");
//			Row row=sh.getRow(5);
//			Cell cell=row.getCell(1);
//			String organizationName1=cell.getStringCellValue()+randomnumber;
//			String actual=organizationName1;
			
			jutil.printStatement(organizationName);
			
			String lastName=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 2)+randomnumber;
			
//			Row row1=sh.getRow(5);
//			Cell cell1=row1.getCell(2);
//			String lastName=cell1.getStringCellValue()+randomnumber;
//			String actual1=lastName;
			
			jutil.printStatement(lastName);
			
//			 wb.close();
			
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
			

			driver.findElement(By.name("user_name")).sendKeys(username);
			driver.findElement(By.name("user_password")).sendKeys(password);
			driver.findElement(By.id("submitButton")).click();
			
			
			driver.findElement(By.linkText("Organizations")).click();
			driver.findElement(By.cssSelector("[title='Create Organization...']")).click();
			driver.findElement(By.name("accountname")).sendKeys(organizationName);
			
			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			
//			String expected=driver.findElement(By.id("dtlview_Organization Name")).getText();
			
//			if(organizationName1.equals(expected))
//			{
//				jutil.printStatement("Test case is passed");
//				jutil.printStatement("organization created");
//			}
//			else
//			{
//				jutil.printStatement("organization not created");
//				jutil.printStatement("Test case is Failed");
//			}
			Thread.sleep(2000);
			driver.findElement(By.xpath("//a[text()='Contacts']")).click();
			driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
			driver.findElement(By.name("lastname")).sendKeys(lastName);
			driver.findElement(By.xpath("//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td/img")).click();
			//String currentid=driver.getWindowHandle();
//			Set<String> allid=driver.getWindowHandles();
//			for(String id:allid)
//			{
//				driver.switchTo().window(id);	
//				if(driver.getTitle().contains("Accounts&action"))
//				{
//					break;
//				}
//			}
			WebDriverUtility.switchToWindowBasedOnTitle(driver,"Organizations");
			
			driver.findElement(By.id("search_txt")).sendKeys(organizationName);
			driver.findElement(By.name("search")).click();
			driver.findElement(By.xpath("//a[contains(@onclick,'set_return_contact_address')]")).click();
			
//			Set<String> allid1=driver.getWindowHandles();
//			for(String id1:allid1)
//			{
//				driver.switchTo().window(id1);
//				if(driver.getTitle().contains("Contacts&action"))
//				{
//					break;
//				}
			WebDriverUtility.switchToWindowBasedOnTitle(driver,"Contacts");

			driver.findElement(By.cssSelector("[title='Save [Alt+S]']")).click();
			
//			String expected1=driver.findElement(By.id("dtlview_Last Name")).getText();
			
			
			WebElement actualLastName=driver.findElement(By.id("dtlview_Last Name"));
		//	WebElement actualOrganizatinName=driver.findElement(By.id("dtlview_Organization Name"));
//			if((organizationName1.equals(expected))&&(lastName.equals(expected1)))
//			{
//				jutil.printStatement("Test case is passed");
//				jutil.printStatement("Contact created with organization");
//			}
//			else
//			{
//				jutil.printStatement("Contact created with organization");
//				jutil.printStatement("Test case is Failed");
//			}
		//	jutil.assertionThroughIfCondition(actualOrganizatinName.getText(), organizationName,"contact with organization");
			jutil.assertionThroughIfCondition(actualLastName.getText(), lastName,"correct last name");
			
			
			WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//			Actions act1=new Actions(driver);
//			act1.moveToElement(img).perform();
			webDriverUtility.mouseOverOnTheElement(img, driver);
			
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//			act1.moveToElement(logout).click().perform();
	
			
			WebDriverUtility.quitBrowser(driver);
}
}
