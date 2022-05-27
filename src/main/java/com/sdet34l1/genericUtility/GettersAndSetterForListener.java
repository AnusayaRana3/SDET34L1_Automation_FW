package com.sdet34l1.genericUtility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vtiger.pomRepository.HomePage;
import com.vtiger.pomRepository.LoginPage;

public class GettersAndSetterForListener {
	
	private static GettersAndSetterForListener instance;
	
	public WebDriver driver;
	public JavaClassUtility javaClassUtility;
	public String userName;
	public String password;
	public String browser;
	public String url;
	public long longTimeOut;
	public int randomNumber;
	public LoginPage loginPage;
	public HomePage homePage;
	public Actions act;
	public WebDriverWait wait;
	public WebDriverUtility webDriverUtility;
	public ExcelCommonClassUtility excelCommonClassUtility;
	public FileClassUtility fileClassUtility;
	
	private GettersAndSetterForListener() {
	}
	
	public static GettersAndSetterForListener getInstance()
	{
		if(instance==null)
		{
			instance=new GettersAndSetterForListener();
		}
		return instance;
	}
	public static void setInstance(GettersAndSetterForListener instance) {
		GettersAndSetterForListener.instance = instance;
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
	public JavaClassUtility getJavaClassUtility() {
		return javaClassUtility;
	}
	public void setJavaClassUtility(JavaClassUtility javaClassUtility) {
		this.javaClassUtility = javaClassUtility;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public long getLongTimeOut() {
		return longTimeOut;
	}
	public void setLongTimeOut(long longTimeOut) {
		this.longTimeOut = longTimeOut;
	}
	public int getRandomNumber() {
		return randomNumber;
	}
	public void setRandomNumber(int randomNumber) {
		this.randomNumber = randomNumber;
	}
	public LoginPage getLoginPage() {
		return loginPage;
	}
	public void setLoginPage(LoginPage loginPage) {
		this.loginPage = loginPage;
	}
	public HomePage getHomePage() {
		return homePage;
	}
	public void setHomePage(HomePage homePage) {
		this.homePage = homePage;
	}
	public Actions getAct() {
		return act;
	}
	public void setAct(Actions act) {
		this.act = act;
	}
	public WebDriverWait getWait() {
		return wait;
	}
	public void setWait(WebDriverWait wait) {
		this.wait = wait;
	}
	public WebDriverUtility getWebDriverUtility() {
		return webDriverUtility;
	}
	public void setWebDriverUtility(WebDriverUtility webDriverUtility) {
		this.webDriverUtility = webDriverUtility;
	}
	public ExcelCommonClassUtility getExcelCommonClassUtility() {
		return excelCommonClassUtility;
	}
	public void setExcelCommonClassUtility(ExcelCommonClassUtility excelCommonClassUtility) {
		this.excelCommonClassUtility = excelCommonClassUtility;
	}
	public FileClassUtility getFileClassUtility() {
		return fileClassUtility;
	}
	public void setFileClassUtility(FileClassUtility fileClassUtility) {
		this.fileClassUtility = fileClassUtility;
	}
	
}
