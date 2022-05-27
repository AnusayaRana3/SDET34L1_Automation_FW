package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

public class OrganizationPage {

	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement createOrganizationLookUpImg;

	@FindBy(xpath="//input[@class='txtBox']")
	private WebElement searchForTxtBox;
	
	@FindBy(id="bas_searchfield")
	private WebElement searchInTxtBox;
	
	@FindBy(xpath="//input[@value=' Search Now '][1]")
	private WebElement searchNowBtn;
	
	@FindBy(xpath="//a[@title='Organizations']")
	private WebElement organizationNameRecord;
	
	@FindBy(xpath="//img[@src='themes/images/findduplicates.gif']")
	private WebElement findDuplicatesLookUpImg;
	
	@FindBy(xpath="//img[@src='themes/softed/images/settingsBox.png']")
	private WebElement organizationSettinLookupImg;
	
	@FindBy(xpath="//tr[@id='row_13']/td/input[@name='selected_id']")
	private WebElement showingOrgNoCheCkBox;
	
	@FindBy(xpath="//input[@value='Mass Edit']")
	private WebElement massEditTxt;
	
//
public 	OrganizationPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

//
	public void clickcreateOrganizationLookUpImg()
	{
		createOrganizationLookUpImg.click();
	}
	
	public void enterAnyNameSearchForTxtBox(String ab)
	{
		searchForTxtBox.sendKeys(ab);
	}
	
	public void clickSearchInAndAssignedToSelectDropdown(WebDriver driver)
	{
		searchInTxtBox.click();
		WebDriverUtility.initializeSelectClass(searchInTxtBox);
		WebDriverUtility.dropDownHandleByValue("assigned_user_id", searchInTxtBox);
		
	}
	
	public void clickSearchNowBtn()
	{
		searchNowBtn.click();
	}
	
	public void searchInOrganizationNameSelectedDropDown(WebDriver driver)
	{
		WebDriverUtility.initializeSelectClass(searchInTxtBox);
		WebDriverUtility.dropDownHandleByValue("accountname", searchInTxtBox);
		//driver.findElement(By.xpath(" //input[@value=' Search Now '][1]")).click();
	}
	
	public void clickOneOrganizationNameRecord()
	{
		organizationNameRecord.click();
	}
	
	public void clickFindDuplicatesLookUpImg()
	{
		findDuplicatesLookUpImg.click();
	}
	
	public void clickOrganizationSettinLookupImg()
	{
		organizationSettinLookupImg.click();
	}
	
	public void clickShowingOrgNoCheCkBox()
	{
		showingOrgNoCheCkBox.click();
	}
	
	public void clickMassEditTxt()
	{
		massEditTxt.click();
	}
}
