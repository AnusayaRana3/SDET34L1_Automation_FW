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
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.OrganizationInformationPage;
import com.vtiger.pomRepository.OrganizationPage;
import com.vtiger.pomRepository.SearchOrganizationContactPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateContactWithOrganizationByPomTest {
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
			jutil.printStatement(organizationName);
			
			String lastName=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 2)+randomnumber;
			
			jutil.printStatement(lastName);
			
			WebDriverUtility.browserSetting(longTimeout, driver);
			webDriverUtility.initializesActions(driver);
			
			LoginPage loginPage=new LoginPage(driver);
			HomePage homePage=new HomePage(driver);
			OrganizationPage organizationPage=new OrganizationPage(driver);
			CreateNewOrganizationPage createNewOrganizationPage=new CreateNewOrganizationPage(driver);
			OrganizationInformationPage organizationInformationPage=new OrganizationInformationPage(driver);
			ContactPage contactPage=new ContactPage(driver);
			CreateNewContactPage createNewContactPage=new CreateNewContactPage(driver);
			SearchOrganizationContactPage searchOrganizationContactPage=new SearchOrganizationContactPage(driver);
			ContactInformationPage contactInformationPage=new ContactInformationPage(driver);
			
			WebDriverUtility.navigateApp(url, driver);

			loginPage.loginAction(username, password);

			homePage.clickOrganization(driver);

			organizationPage.clickcreateOrganizationLookUpImg();

			createNewOrganizationPage.enterOrganizationName(organizationName);
			createNewOrganizationPage.saveOrganizationAndIndustryAndType();
			jutil.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), organizationName,"Organization Created");

//			Thread.sleep(2000);
			WebDriverUtility.explicitlywait(driver, longTimeout);

			homePage.clickContact(driver);

			contactPage.clickcreateContactLookUpImg();

			createNewContactPage.enterLastNameAndSwitchToSearchOrganization(lastName,driver);
			
			searchOrganizationContactPage.selectOrganization(driver, organizationName);
			
			WebDriverUtility.switchToWindowBasedOnTitle(driver,"Contacts");

			createNewContactPage.saveContact();
			
			jutil.assertionThroughIfCondition(contactInformationPage.getLastName(), lastName,"Correct Last Name");
			
			homePage.signout(driver,webDriverUtility);
			
			WebDriverUtility.quitBrowser(driver);
	}


}
