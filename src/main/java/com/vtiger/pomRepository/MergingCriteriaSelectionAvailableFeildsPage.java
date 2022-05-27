package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MergingCriteriaSelectionAvailableFeildsPage {

	@FindBy(xpath="//select[@id='availList']/option[text()='Organization Name']")
	private WebElement selectOrganizationNameTab;
	
	@FindBy(xpath="//input[@name='Button']")
	private WebElement leftToRightArrowBtn;
	
	@FindBy(xpath="//input[@value='Find Duplicates']")
	private WebElement findDuplicatesBtn;
	
//	
public MergingCriteriaSelectionAvailableFeildsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
//	
	public void selectOrganizationNameTabFromAvailableFields()
	{
		selectOrganizationNameTab.click();
	}
	
	public void selectleftToRightArrowBtn()
	{
		leftToRightArrowBtn.click();
	}
	
	public void clickfindDuplicatesBtn()
	{
		findDuplicatesBtn.click();
	}
}
