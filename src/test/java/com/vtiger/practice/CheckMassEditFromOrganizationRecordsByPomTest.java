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
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.OrganizationMassEditRecordsPage;
import com.vtiger.pomRepository.OrganizationPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CheckMassEditFromOrganizationRecordsByPomTest {
	public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
		JavaClassUtility jutil=new JavaClassUtility();
		WebDriver driver=null;
		
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
			
			LoginPage loginPage=new LoginPage(driver);
			HomePage homePage=new HomePage(driver);
			OrganizationPage organizationPage=new OrganizationPage(driver);
			OrganizationMassEditRecordsPage organizationMassEditRecordsPage=new OrganizationMassEditRecordsPage(driver);
			WebDriverUtility.navigateApp(url, driver);
		
			loginPage.loginAction(username, password);
	//Navigate to Organizations
			homePage.clickOrganization(driver);
		
	//	driver.findElement(By.xpath("//tr[@id='row_13']/td/input[@name='selected_id']")).click();
			organizationPage.clickShowingOrgNoCheCkBox();
	//	driver.findElement(By.xpath("//input[@value='Mass Edit']")).click();
			organizationPage.clickMassEditTxt();
	//	String pagedisplay=driver.findElement(By.xpath("//td[text()='Mass Edit - Records Fields']")).getText();
		jutil.printStatement(organizationMassEditRecordsPage.getOrganizationMassEditRecordsPageMsg());
		
	//	homePage.signout(driver);
		
		WebDriverUtility.quitBrowser(driver);
}
}
