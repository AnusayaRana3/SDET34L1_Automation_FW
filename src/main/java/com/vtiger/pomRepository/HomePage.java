package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

//Create the class as Webpage name and make it as Public
public class HomePage {
	
	@FindBy(linkText="More")
	private WebElement moreDropdown;
	
	@FindBy(name="Campaigns")
	private WebElement campaignsTab;
	
	@FindBy(xpath="//img[@src='themes/softed/images/user.PNG']")
	private WebElement administator;
	
	@FindBy(linkText="Sign Out")
	private WebElement signOutLink;
	
	@FindBy(linkText="Products")
	private WebElement productsTab;

	@FindBy(linkText="Contacts")
	private WebElement contactsTab;
	
	@FindBy(linkText="Organizations")
	private WebElement organizationsTab;
	
	@FindBy(xpath="//td[@class='tabUnSelected']//a[text()='Documents']")
	private WebElement documentsTab;
	
	@FindBy(xpath="//span[text()='No Duplicate Records']")
	private WebElement noDuplicateRecordsMsg;
	
/**
 * Initialize the driver address to all the elements through Constructor and make it as Public
 * @param driver
 */
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	

/**
 * Business Library
 * @param driver
 */
	public void clickCampaign(WebDriver driver, WebDriverUtility webDriverUtility)
	{
		webDriverUtility.mouseOverOnTheElement(moreDropdown, driver);
		campaignsTab.click();
	}
	
	public void signout(WebDriver driver, WebDriverUtility webDriverUtility)
	{
		webDriverUtility.mouseOverOnTheElement(administator, driver);
		signOutLink.click();
	}
	
	public void clickProduct(WebDriver driver)
	{
		productsTab.click();
	}
	
	public WebElement getMoreDropdown(WebDriver driver)
	{
		return moreDropdown;
	}
	
	public void clickContact(WebDriver driver)
	{
		contactsTab.click();
	}
	
	public void clickOrganization(WebDriver driver)
	{
		organizationsTab.click();
	}
	
	public void clickDocument(WebDriver driver)
	{
		documentsTab.click();
	}
	
	public String getnoDuplicateRecordsMsg()
	{
		return noDuplicateRecordsMsg.getText();
	}
}
