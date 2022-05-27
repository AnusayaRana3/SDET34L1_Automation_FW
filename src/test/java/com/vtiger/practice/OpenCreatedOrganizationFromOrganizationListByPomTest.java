package com.vtiger.practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.FileClassUtility;
import com.sdet34l1.genericUtility.InterfaceCPathUtility;
import com.sdet34l1.genericUtility.JavaClassUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.OrganizationInformationPage;
import com.vtiger.pomRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class OpenCreatedOrganizationFromOrganizationListByPomTest {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
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
		
		int randomnumber =jutil.getRandomNumber(1000);
		
String organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
		
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
			WebDriverUtility.browserSetting(longTimeout, driver);
			webDriverUtility.initializesActions(driver);
			
			LoginPage loginPage=new LoginPage(driver);
			HomePage homePage=new HomePage(driver);
			OrganizationPage organizationPage=new OrganizationPage(driver);
			CreateNewOrganizationPage createNewOrganizationPage=new CreateNewOrganizationPage(driver);
			OrganizationInformationPage organizationInformationPage=new OrganizationInformationPage(driver);
		
			WebDriverUtility.navigateApp(url, driver);
			loginPage.loginAction(username, password);
			homePage.clickOrganization(driver);
			organizationPage.clickcreateOrganizationLookUpImg();
			createNewOrganizationPage.enterOrganizationName(organizationname);
			createNewOrganizationPage.saveOrganizationAndIndustryAndType();
			
			jutil.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), organizationname,"Correct Organization Name Created");

			homePage.clickOrganization(driver);
			organizationPage.enterAnyNameSearchForTxtBox(organizationname);
		//driver.findElement(By.xpath("//input[@class='txtBox']")).sendKeys(organizationname);
			organizationPage.clickSearchInAndAssignedToSelectDropdown(driver);
		//WebElement dropdown=driver.findElement(By.xpath("//select[@id='bas_searchfield']"));
//		WebDriverUtility.initializeSelectClass(dropdown);
//		WebDriverUtility.dropDownHandleByValue("accountname", dropdown);
//		driver.findElement(By.xpath(" //input[@value=' Search Now '][1]")).click();
			organizationPage.searchInOrganizationNameSelectedDropDown(driver);
		
//		driver.findElement(By.xpath("//a[@title='Organizations']")).click();
			organizationPage.clickOneOrganizationNameRecord();
			
//		//String information=driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]")).getText();
//		//System.out.println(information);
			String information=	organizationInformationPage.getOrganizationInformationMsg();
			System.out.println(information);
			homePage.signout(driver,webDriverUtility);
		
			WebDriverUtility.quitBrowser(driver);
}
}
