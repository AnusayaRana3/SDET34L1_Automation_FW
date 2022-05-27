package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationMassEditRecordsPage {

	@FindBy(xpath="//td[text()='Mass Edit - Records Fields']")
	private WebElement massEditRecordsFieldsText;
	
//
	public OrganizationMassEditRecordsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
//
	public String getOrganizationMassEditRecordsPageMsg()
	{
		return massEditRecordsFieldsText.getText();
		
	}
}
