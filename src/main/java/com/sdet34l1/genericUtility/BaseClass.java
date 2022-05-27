package com.sdet34l1.genericUtility;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * This class contains implimentations of all basic configuration annotations
 * @author ANUSAYA RANA
 *Baseclass is Edited
 */
public class BaseClass {
	
	public String username;
	public String password;
	public String browser;
	public long longTimeout;
	public int randomnumber;
	public JavaClassUtility javaClassUtility;
	public WebDriver driver;
	public LoginPage loginPage;
	public HomePage homePage;
	public Actions act;
	public WebDriverUtility webDriverUtility;
	public static WebDriver staticDriver;


	/**
	 * In this annotation we open the database, the property file,open the excelsheet
	 */
	@BeforeSuite(groups="baseclass")
	public void beforeSuite1Test()
	{
		//Open Database if it is required
		
		try {
			FileClassUtility.openPropertyFile(InterfaceCPathUtility.PROPERTYFILEPATH);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ExcelCommonClassUtility.openExcel(InterfaceCPathUtility.EXCELFILEPATH);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * In this annotation we fetch the common data from the property file and 
	 * create the instance for all generic utility
	 * launch browser
	 * do the browser seeting(maximize,implicit wait,action class initialization,explicit wait instance initialization)
	 * Create the instance for common object repository class
	 */
	
	//@Parameters(value="browser")
	@BeforeClass(groups="baseclass")
	public void beforeclass1Test()//(String browser)
	{
		//craete instance for all generic utility
		javaClassUtility=new JavaClassUtility();
		
		String url=FileClassUtility.getDataFromPropertyFile("url");
		//username=FileClassUtility.getDataFromPropertyFile("username");
		username=System.getProperty("USERNAME");
		
		//password=FileClassUtility.getDataFromPropertyFile("password");
		password=System.getProperty("PASSWORD");
		
		//String browser=FileClassUtility.getDataFromPropertyFile("browser");
		browser=System.getProperty("BROWSER");
		
		String timeout=FileClassUtility.getDataFromPropertyFile("timeout");
		
		longTimeout=javaClassUtility.stringToLong(timeout);
		
		randomnumber=javaClassUtility.getRandomNumber(1000);
		
		//launch browser instance
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
		
		staticDriver=driver;
		
	//browser settings(maximize,implicit wait,action class, initialization,explicit wait instance initialization)
		WebDriverUtility.browserSetting(longTimeout, driver);
		webDriverUtility=new WebDriverUtility();
		webDriverUtility.initializesActions(driver);
		WebDriverUtility.explicitlywait(driver, longTimeout);
		
		//Create the instance for common object repository
		loginPage=new LoginPage(driver);
		homePage=new HomePage(driver);
		
		//navigate to the application
		WebDriverUtility.navigateApp(url, driver);
		
	}
	
	/**
	 * In this annotation we will do login actions
	 */
	@BeforeMethod(groups="baseclass")
	public void beforeMethod1Test()
	{
		loginPage.loginAction(username, password);
	}
	
	/**
	 * In this annotation we will do signout actions
	 */
	@AfterMethod(groups="baseclass")
	public void afterMethod1Test()
	{
		homePage.signout(driver, webDriverUtility);
	}
	
	/**
	 * In this annotation we will close the browser instance
	 */
	@AfterClass(groups="baseclass")
	public void afterClass1Test()
	{
		WebDriverUtility.quitBrowser(driver);
	}
	
	/**
	 * In this annotation we will close Database
	 * close Excelsheet
	 */
	@AfterSuite(groups="baseclass")
	public void afterSuite1Test()
	{
		//close database connection code
		ExcelCommonClassUtility.writeExcel(InterfaceCPathUtility.EXCELWRITEPATH);

		ExcelCommonClassUtility.closeExcel();
	}
	
}
