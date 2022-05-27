package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContactPage {

	@FindBy(name="lastname")
	private WebElement lastNameTxt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath="//td[contains(.,'Organization Name') and @class='dvtCellLabel']/following-sibling::td/img")
	private WebElement clickorganizationLookUpImg;
	
//
public CreateNewContactPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

//
	public void enterLastNameAndSwitchToSearchOrganization(String value, WebDriver driver)
	{
		lastNameTxt.sendKeys(value);
		clickorganizationLookUpImg.click();
	}
	
	public void enterLastNameAndSaveContact(String value)
	{
		lastNameTxt.sendKeys(value);
		saveBtn.click();
	}
	
	public void saveContact()
	{
		saveBtn.click();
	}
}
