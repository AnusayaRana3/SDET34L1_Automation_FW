package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationSettingPage {

	@FindBy(xpath="//td[@valign='bottom']/b")
	private WebElement organizationSettingBtn;
	
	
//
	public OrganizationSettingPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
//
	public String getOrganizationSettingPageMsg()
	{
		return organizationSettingBtn.getText();
		
	}
}
