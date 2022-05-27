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
import com.vtiger.pomRepository.CreateNewDocumentPage;
import com.vtiger.pomRepository.DocumentInformationPage;
import com.vtiger.pomRepository.DocumentPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentWithIframByPomTest {
public static void main(String[] args) throws  IOException, InterruptedException {
		
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
		
		String title=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 1)+randomnumber;
		String documentpath=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 2);
		String documentDescription=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 3);
		
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
		DocumentPage documentPage=new DocumentPage(driver);
		CreateNewDocumentPage createNewDocumentPage=new CreateNewDocumentPage(driver);
		DocumentInformationPage documentInformationPage=new DocumentInformationPage(driver);
		
		
		WebDriverUtility.navigateApp(url, driver);
	
		loginPage.loginAction(username, password);
		homePage.clickDocument(driver);

		documentPage.clickcreateDocumentLookUpImg();
	
		createNewDocumentPage.enterTitleName(title);
	
		
		createNewDocumentPage.switchToFrameAndDescriptionSelectAllAndSwitchToMainFrame(driver, documentDescription);
		
		createNewDocumentPage.clickBoldLetter();
		
		createNewDocumentPage.clickItalicLetter();
		
		createNewDocumentPage.choosenFileOption(documentpath);

		createNewDocumentPage.saveBt();
	
	jutil.assertionThroughIfCondition(documentInformationPage.getTitleName(), title,"Correct Document Title created successfully");
	
	homePage.signout(driver,webDriverUtility);
	
		ExcelCommonClassUtility.setDataInExcel("Documents", 2 ,4,"Documents page is Displayed");
		ExcelCommonClassUtility.setDataInExcel("Contacts", 2 ,5,"pass");
		ExcelCommonClassUtility.writeExcel(InterfaceCPathUtility.EXCELWRITEPATH);
	
	WebDriverUtility.quitBrowser(driver);
}
}
