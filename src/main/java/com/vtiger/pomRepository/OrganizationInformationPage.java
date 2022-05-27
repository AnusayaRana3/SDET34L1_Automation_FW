package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationInformationPage {

	@FindBy(id="dtlview_Organization Name")
	private WebElement organizationNameText;
	
	@FindBy(xpath="//span[@id='dtlview_Industry']")
	private WebElement industryDrpd;
	
	@FindBy(xpath="//span[@id='dtlview_Type']")
	private WebElement accountTypeDrpd;
	
	@FindBy(xpath="//span[contains(text(),'Organization Information')]")
	private WebElement organizationInformationMsg;
//
public OrganizationInformationPage(WebDriver driver)	
{
	PageFactory.initElements(driver, this);
}

//
	public String getOrganizationName()
	{
		return organizationNameText.getText();
		
	}
	
	public String getIndustryAs()
	{
		return industryDrpd.getText();
		
	}
	
	public String getAccountTypeAs()
	{
		return accountTypeDrpd.getText();
		
	}
	
	public String getOrganizationInformationMsg()
	{
		return organizationInformationMsg.getText();
		
	}
	
	public WebElement getOrganizationInformationMsgElement()
	{
		return organizationInformationMsg;
		
	}
}
