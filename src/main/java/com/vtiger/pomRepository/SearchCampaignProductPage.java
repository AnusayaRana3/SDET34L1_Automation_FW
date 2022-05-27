package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

public class SearchCampaignProductPage {

	@FindBy(id="search_txt")
	private WebElement searchTxt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
	

public SearchCampaignProductPage(WebDriver driver)	
{
		PageFactory.initElements(driver, this);
}
	
	public void selectProduct(String productname,WebDriver driver)
	{
		WebDriverUtility.switchToWindowBasedOnTitle(driver,"Products");
		searchTxt.sendKeys(productname);
		searchBtn.click();
		driver.findElement(By.xpath("//a[.='"+productname+"']")).click();
	}
}

