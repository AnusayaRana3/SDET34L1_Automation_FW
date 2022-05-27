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
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;
import com.vtiger.pomRepository.ProductInformationPage;
import com.vtiger.pomRepository.ProductPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductsFromPropertiesByPomTest {
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

		String productactual=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 2)+randomnumber;
		
		WebDriverUtility.browserSetting(longTimeout, driver);
		webDriverUtility.initializesActions(driver);
		LoginPage loginPage=new LoginPage(driver);
		HomePage homePage=new HomePage(driver);
		ProductPage productPage=new ProductPage(driver);
		CreateNewProductPage createNewProductPage=new CreateNewProductPage(driver);
		ProductInformationPage productInformationPage=new ProductInformationPage(driver);
		
		WebDriverUtility.navigateApp(url, driver);

		loginPage.loginAction(username, password);
		homePage.clickProduct(driver);
		
		productPage.clickcreateProductLookUpImg();
		
		createNewProductPage.enterProductNameAndSave(productactual);

//		driver.findElement(By.xpath("//a[text()='Products']")).click();
//		driver.findElement(By.xpath("//img[@title='Create Product...']")).click();
//		driver.findElement(By.xpath("//input[@name='productname']")).sendKeys(productactual);
//		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

	//	String productexpected=driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();

		jutil.assertionThroughIfCondition(productInformationPage.getproductNameText(), productactual,"correct product name");
//		if(productactual.equals(productexpected))
//		{
//			jutil.printStatement("Correct product Name");	
//		}
//		else
//		{
//			jutil.printStatement("Incorrectproduct Name");
//		}

	homePage.signout(driver,webDriverUtility);
		WebDriverUtility.quitBrowser(driver);
	}
}
