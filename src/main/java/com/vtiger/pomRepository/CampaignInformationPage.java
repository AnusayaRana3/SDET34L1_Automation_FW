package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CampaignInformationPage {

	@FindBy(xpath="//span[@id='dtlview_Campaign Name']")
	private WebElement campaignNameText;
	
	@FindBy(xpath="//span[@id='dtlview_Product']/a")
	private WebElement productNameText;
	
	public CampaignInformationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
//	
	public String getProductName()
	{
		return productNameText.getText();
		
	}
	
	public String getCampaignName()
	{
		return campaignNameText.getText();
	}
}
