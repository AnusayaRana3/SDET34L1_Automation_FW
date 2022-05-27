package com.vtiger.practice;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
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

public class CreateDocumentWithIframByGenericTest {
public static void main(String[] args) throws AWTException, IOException, InterruptedException {
		
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
		
		//Excel File	
//		FileInputStream fisexcel=new FileInputStream("./src/test/resources/excel.xlsx");
//		Workbook wb=WorkbookFactory.create(fisexcel);
		String Title=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 1)+randomnumber;
		String documentpath=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 2)+randomnumber;
		String documentDescription=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 3)+randomnumber;
		
//		Random r=new Random();
//		int num=r.nextInt(1000);
//		String Title=wb.getSheet("Documents").getRow(2).getCell(1).getStringCellValue()+randomnumber;
//		String documentpath=wb.getSheet("Documents").getRow(2).getCell(2).getStringCellValue();
//		String documentDescription=wb.getSheet("Documents").getRow(2).getCell(3).getStringCellValue();
		
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
	
//	driver.manage().window().maximize();
//	driver.manage().timeouts().implicitlyWait(longTimeout,TimeUnit.SECONDS);
//	driver.get(url);
		WebDriverUtility.navigateApp(url, driver);
		WebDriverUtility.browserSetting(longTimeout, driver);	
	
	driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(username);
	driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(password);
	driver.findElement(By.id("submitButton")).click();
	
	driver.findElement(By.xpath("//td[@class='tabUnSelected']//a[text()='Documents']")).click();
	driver.findElement(By.xpath("//img[@title='Create Document...']")).click();
	driver.findElement(By.xpath("//input[@name='notes_title']")).sendKeys(Title);
	
	WebElement iframe=driver.findElement(By.xpath("//iframe[contains(@title,'Rich text editor')]"));
	driver.switchTo().frame(iframe);
	
	driver.findElement(By.xpath("//body[@class='cke_show_borders']")).sendKeys(documentDescription);
	
	Robot robot=new Robot();
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_A);
	robot.keyRelease(KeyEvent.VK_CONTROL);
	robot.keyRelease(KeyEvent.VK_A);
	
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//span[@class='cke_button']//a[@id='cke_5']")).click();
	driver.findElement(By.xpath("//span[@class='cke_button']//a[@id='cke_6']")).click();
	
	WebElement filechoose=driver.findElement(By.xpath("//input[@id='filename_I__']"));
	filechoose.sendKeys(documentpath);
	
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
	
	//validate
	WebElement oldtitle=driver.findElement(By.xpath("//span[@id='dtlview_Title']"));
//	if(Title.equals(oldtitle))
//	{
//		jutil.printStatement("title is:" +oldtitle);	
//    }
	jutil.assertionThroughIfCondition(oldtitle.getText(), Title,"Correct Title created successfully");
	
	//data pass in excel
//	if(driver.getTitle().contains("Documents"))
//	{
//		wb.getSheet("Documents").getRow(2).createCell(4).setCellValue("Documents page is Displayed");
//		wb.getSheet("Documents").getRow(2).createCell(5).setCellValue("pass");
		
		ExcelCommonClassUtility.setDataInExcel("Documents", 2 ,4,"Documents page is Displayed");
		ExcelCommonClassUtility.setDataInExcel("Contacts", 2 ,5,"pass");
//	}

	//Logout
	
	WebElement img=driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
//	Actions act1=new Actions(driver);
//	act1.moveToElement(img).perform();
	webDriverUtility.mouseOverOnTheElement(img, driver);
	
	WebElement logout=driver.findElement(By.xpath("//a[text()='Sign Out']"));
//	act1.moveToElement(logout).click().perform();
	webDriverUtility.mouseOverOnTheElement(logout, driver);
	
	//Write the Data 
//	FileOutputStream fos=new FileOutputStream("./src/test/resources/excel.xlsx");
//	wb.write(fos);
	
	ExcelCommonClassUtility.writeExcel(InterfaceCPathUtility.EXCELWRITEPATH);
	
//	wb.close();
//	driver.quit();
	WebDriverUtility.quitBrowser(driver);
}
}
