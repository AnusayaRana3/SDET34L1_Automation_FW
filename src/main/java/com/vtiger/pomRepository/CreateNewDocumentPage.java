package com.vtiger.pomRepository;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

public class CreateNewDocumentPage {

	@FindBy(name="notes_title")
	private WebElement titleTxt;
	
	@FindBy(xpath="//iframe[contains(@title,'Rich text editor')]")
	private WebElement frameText;
	
	@FindBy(xpath="//body[@class='cke_show_borders']")
	private WebElement descriptionsTxt;
	
	@FindBy(xpath="//span[@class='cke_button']//a[@id='cke_5']")
	private WebElement boldLtr;
	
	@FindBy(xpath="//span[@class='cke_button']//a[@id='cke_6']")
	private WebElement italicLtr;
	
	@FindBy(xpath="//input[@id='filename_I__']")
	private WebElement fileOpt;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
//
public CreateNewDocumentPage(WebDriver driver)	
{
	PageFactory.initElements(driver, this);
}

//
	public void enterTitleName(String title)
	{
		titleTxt.sendKeys(title);
	}
	
	public void clickBoldLetter()
	{
		boldLtr.click();
	}
	
	public void clickItalicLetter()
	{
		italicLtr.click();
	}
	
	public void choosenFileOption(String documentpath)
	{
		fileOpt.sendKeys(documentpath);
	}
	
	public void saveBt()
	{
		saveBtn.click();
	}
	
	public void switchToFrameAndDescriptionSelectAllAndSwitchToMainFrame(WebDriver driver,String documentDescription)
	{
		WebDriverUtility.switchToFrame(driver, frameText);
		descriptionsTxt.sendKeys(documentDescription,Keys.CONTROL+"a");
		WebDriverUtility.switchBackToHome(driver);
	}
}
