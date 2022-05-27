package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

public class CreateNewOrganizationPage {

	@FindBy(name="accountname")
	private WebElement organizationNameTxt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//select[@name='industry']")
	private WebElement industryDropdown;
	
	@FindBy(xpath="//select[@name='accounttype']")
	private WebElement accountTypeDropdown;
	
	@FindBy(xpath="//td[@class='fdvtCellLabel']//following-sibling::td[1]//input[@value='U']")
	private WebElement assignedToTab;
	
	@FindBy(xpath="//input[@value='T']")
	private WebElement groupBtn;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBTn;
	
//
public CreateNewOrganizationPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}


//
	public void enterOrganizationName(String organizationName)
	{
		organizationNameTxt.sendKeys(organizationName);
	//	saveBtn.click();
	}
	
	public void selectIndustryDropdown(WebDriver driver)
	{
		WebDriverUtility.initializeSelectClass(industryDropdown);
		WebDriverUtility.dropDownHandleByValue("Education", industryDropdown);
	}
	
	public void selectAccountTypeDropdown(WebDriver driver)
	{
		WebDriverUtility.initializeSelectClass(accountTypeDropdown);
		WebDriverUtility.dropDownHandleByValue("Press", accountTypeDropdown);
	}
	
	public void saveOrganizationAndIndustryAndType()
	{
		saveBtn.click();
	}
	
	public void goToAssignedToAndClickGroup()
	{
		//assignedToTab.click();
		groupBtn.click();
	}
}
