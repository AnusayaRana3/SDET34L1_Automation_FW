package com.sdet34l1.genericUtility;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
/**
 * This class is used to maintain all the WebDriver common actions
 * @author ANUSAYA RANA
 *
 */
public class WebDriverUtility
{
	static WebDriverWait wait;
	Actions act;
	static Select select;
	JavascriptExecutor jse;
	static JavaClassUtility javaClassUtility;
	/**
	 * This method is used to navigate to Application
	 * @param url
	 * @param driver
	 */
	public static void navigateApp(String url,WebDriver driver)
	{
		driver.get(url);
	}
	
	/**
	 * This method is used to call maximize the browser and implicitly wait
	 * @param longTimeOut
	 * @param driver
	 */
	public static void browserSetting(long longTimeOut,WebDriver driver) {
		maximizeBrowser(driver);
		waitTillPageLoad(longTimeOut,driver);
	}
	
	/**
	 * This method is used to maximize the browser
	 * @param driver
	 */
	public static void maximizeBrowser(WebDriver driver)
	{
		driver.manage().window().maximize();
	}
	
	/**
	 * This method is used to implicitly wait till page load
	 * @param longTimeOut
	 * @param driver
	 */
	public static void waitTillPageLoad(long longTimeOut,WebDriver driver)
	{
		driver.manage().timeouts().implicitlyWait(longTimeOut,TimeUnit.SECONDS);
	}
	
	/**
	 * This method is used to intitialize the Action class
	 * @param driver
	 */
	public void initializesActions(WebDriver driver)
	{
		act=new Actions(driver);
	}
	
	/**
	 * This method is used to mouse over on the Element
	 * @param moreLink
	 * @param driver
	 */
	public void mouseOverOnTheElement(WebElement moreLink,WebDriver driver)
	{
		act.moveToElement(moreLink).perform();
	}
	
	/**
	 * This method is used to double click on the Element
	 * @param element
	 */
	public void doubleClick(WebElement element,WebDriver driver )
	{
		act.doubleClick(element).click().perform();
	}
	
	/**
	 * This method is used to double click on Webpage
	 */
	public void doubleClick(WebDriver driver)
	{
		act.doubleClick();
	}
	
	/**
	 * This method is used to close the browser Instance
	 * @param driver
	 */
	public static void quitBrowser(WebDriver driver)
	{
		driver.quit();
	}
	
	/**
	 * This method is used to wait the control till the particular element is clickable
	 * @param element
	 */
	public static void waitUntilElementClickable(WebElement element)
	{
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	/**
	 * This method is used to wait the control till the particular element is visible
	 * @param element
	 */
	public static void waitUntilElementVisible(WebElement element)
	{
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	/**
	 * This method is used to initialize wait instance
	 * @param driver
	 * @param timeOut
	 */
	public static void explicitlywait(WebDriver driver,long timeOut)
	{
		wait=new WebDriverWait(driver,timeOut);
	}
	
	/**
	 * This method is used to switch the window based on title
	 * @param driver
	 * @param partialText
	 */
	public static void switchToWindowBasedOnTitle(WebDriver driver,String partialText)
	{
		Set<String> sessionIDs=driver.getWindowHandles();
		for(String id:sessionIDs)
		{
			driver.switchTo().window(id);
			if(driver.getTitle().contains(partialText))
			{
				break;
			}
		}
	}
	
	/**
	 * This method is used to initialize the Select Class
	 * @param element
	 */
	public static void initializeSelectClass(WebElement element)
	{
		select=new Select(element);
	}
	
	/**
	 * This method is used to handle the dropdown by Index value
	 * @param element
	 * @param index
	 */
	public static void dropDownHandelByIndex(WebElement element,int index)
	{
		select.selectByIndex(index);
	}
	
	/**
	 * This method is used to handle the dropdown by name and IDs
	 * @param value
	 * @param element
	 */
	public static void dropDownHandleByValue(String value,WebElement element)
	{
		select.selectByValue(value);
	}
	
	/**
	 * This method is used to handle the dropdown by visibleText
	 * @param element
	 * @param visibletext
	 */
	public static void dropDownHandleByVisibleText(WebElement element,String visibletext)
	{
		select.selectByVisibleText(visibletext);
	}
	
	/**
	 * This method is used to switch the frame by index
	 * @param driver
	 * @param index
	 */
	public static void switchToFrame(WebDriver driver,int index)
	{
		driver.switchTo().frame(index);
	}
	
	/**
	 * This method is used to switch frame by nameorIDs
	 * @param driver
	 * @param nameorIDs
	 */
	public static void switchToFrame(WebDriver driver,String nameorIDs)
	{
		driver.switchTo().frame(nameorIDs);
	}
	
	/**
	 * This method is used to switch the frame by WebElement
	 * @param driver
	 * @param element
	 */
	public static void switchToFrame(WebDriver driver,WebElement element)
	{
		driver.switchTo().frame(element);
	}
	
	/**
	 * This method is used to switch the frame to parent frame
	 * @param driver
	 */
	public static void switchToParentFrame(WebDriver driver )
	{
		driver.switchTo().parentFrame();
	}
	
	/**
	 * This method is used to switch to the main frame
	 * @param driver
	 */
	public static void switchBackToHome(WebDriver driver)
	{
		driver.switchTo().defaultContent();
	}
	
	/**
	 * This method is used to initialize the JavascriptExecutor
	 * @param driver
	 */
	public void initializeJavaScript(WebDriver driver)
	{
		jse=(JavascriptExecutor)driver;
	}
	
	/**
	 * This method is used to pass or send the data through JavascriptExecutor
	 * @param element
	 * @param data
	 */
	public void enterDataThroughJavaScript(WebElement element,String data)
	{
		jse.executeScript("arguments[0].value=arguments[1]",element,data);
	}
	
	/**
	 * This method is used to perform click Using JavascriptExecutor
	 * @param element
	 */
	public void clickThroughJavaScript(WebElement element)
	{
		jse.executeScript("arguments[0].click()",element);
	}
	
	/**
	 * This method is used to navigate the Application by using JavascriptExecution
	 * @param url
	 */
	public void navigateApplicationThroughJavaScript(String url)
	{
		jse.executeScript("window.location=arguments[0]",url);
	}
	
	/**
	 * This method is used to scroll upto provided height
	 * @param height
	 */
	public void scrollToSpecifiedHeight(String height)
	{
		jse.executeScript("window.scrollBy(0,"+height+")");
	}
	
	/**
	 * This method is used to scroll upto bottom
	 */
	public void scrollToBottom()
	{
		jse.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}
	
	/**
	 * This method is used to scroll upto perticular element
	 * @param element
	 */
	public void scrollUpToElement(WebElement element)
	{
		jse.executeScript("arguments[0].scrollIntoView()",element);
	}
	
	/**
	 * This Method is used to take Screenshot
	 * @param fileName
	 * @param driver
	 * @return
	 * @throws IOException 
	 */
	public static String takeScreenshot(String fileName,WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg=new File("./screenshot/"+fileName+"_"+javaClassUtility.getDateTimeInFormat()+".png");
		System.out.println(trg.getAbsolutePath());
		FileUtils.copyFile(src, trg);
		return trg.getAbsolutePath();
	}
}