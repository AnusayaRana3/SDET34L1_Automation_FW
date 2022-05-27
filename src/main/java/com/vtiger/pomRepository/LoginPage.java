package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	@FindBy(name="user_name")
	private WebElement userNameTxt;
	
	@FindBy(name="user_password")
	private WebElement passwordTxt;
	
	@FindBy(id="submitButton")
	private WebElement loginBtn;
	
	/**
	 * Intitialize Constructor
	 * @param driver
	 */
	public LoginPage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	

/**
 * business Library
 * @param username
 * @param password
 */
	public void loginAction(String username,String password)
	{
		userNameTxt.sendKeys(username);
		passwordTxt.sendKeys(password);
		loginBtn.click();
	}
}
