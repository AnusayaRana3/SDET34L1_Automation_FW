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
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateCampaignWithProductsByPomTest {
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
		WebDriverUtility.browserSetting(longTimeout, driver);
		WebDriverUtility.explicitlywait(driver, longTimeout);
		webDriverUtility.initializesActions(driver);

		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		CampaignPage campaignPage=new CampaignPage(driver);
		CreateNewCampaignPage createCampaignPage=new CreateNewCampaignPage(driver);
		CampaignInformationPage campaignInformationPage=new CampaignInformationPage(driver);
		ProductPage productPage=new ProductPage(driver);
		CreateNewProductPage createNewProductPage=new CreateNewProductPage(driver);

		WebDriverUtility.navigateApp(url, driver);

		loginPage.loginAction(username, password);

		homePage.clickProduct(driver);

		productPage.clickcreateProductLookUpImg();

		createNewProductPage.enterProductNameAndSave(productname);

		//			WebDriverUtility.waitUntilElementClickable(driver.findElement(By.linkText("More")));
		WebDriverUtility.waitUntilElementClickable(homePage.getMoreDropdown(driver));

		homePage.clickCampaign(driver,webDriverUtility);

		campaignPage.clickcreateCampaignLookUpImg();

		createCampaignPage.enterCampaignNameAndEnterAndSwitchToSearchProduct(campaignname,driver);

		WebDriverUtility.switchToWindowBasedOnTitle(driver,"Campaigns");

		createCampaignPage.saveCampaign();	

		jutil.assertionThroughIfCondition(campaignInformationPage.getCampaignName(), campaignname,"campaign with product");
		jutil.assertionThroughIfCondition(campaignInformationPage.getProductName(), productname,"correct product name");

	//	homePage.signout(driver);

		WebDriverUtility.quitBrowser(driver);
	}
}
