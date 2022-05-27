package com.vtiger.practice;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.FileClassUtility;
import com.sdet34l1.genericUtility.InterfaceCPathUtility;
import com.sdet34l1.genericUtility.JavaClassUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;
import com.vtiger.pomRepository.ContactInformationPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithExternalExcelByPomTest {
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

		String value=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 1)+randomnumber;
		jutil.printStatement(value);

		if(browser.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();	
		}
		else if(browser.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}

		WebDriverUtility.browserSetting(longTimeout, driver);
		webDriverUtility.initializesActions(driver);

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ContactPage contactPage=new ContactPage(driver);
		CreateNewContactPage createNewContactPage=new CreateNewContactPage(driver);
		ContactInformationPage contactInformationPage=new ContactInformationPage(driver);
		WebDriverUtility.navigateApp(url, driver);

		loginPage.loginAction(username, password);

		if(driver.getTitle().contains("Home"))
		{
			ExcelCommonClassUtility.setDataInExcel("Contacts", 15 ,5,"Home page should displayed");
			ExcelCommonClassUtility.setDataInExcel("Contacts", 15 ,6," TestCase pass");

		}

		homePage.clickContact(driver);

		if(driver.getTitle().contains("Contacts"))
		{

			ExcelCommonClassUtility.setDataInExcel("Contacts", 16 ,5,"Contacts page should displayed");
			ExcelCommonClassUtility.setDataInExcel("Contacts", 16 ,6," TestCase pass");

		}

		contactPage.clickcreateContactLookUpImg();
		createNewContactPage.enterLastNameAndSaveContact(value);
		jutil.assertionThroughIfCondition(contactInformationPage.getLastName(), value,"Contacts Lastname");
		//	createNewContactPage.enterLastNameAndSwitchToSearchOrganization(value,driver);

		//homePage.signout(driver);

		ExcelCommonClassUtility.writeExcel(InterfaceCPathUtility.EXCELWRITEPATH);

		//			wb.close();
		ExcelCommonClassUtility.closeExcel();
		//			driver.quit();
		WebDriverUtility.quitBrowser(driver);
	}
}
