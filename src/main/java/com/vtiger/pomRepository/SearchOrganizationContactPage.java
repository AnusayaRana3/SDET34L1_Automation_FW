package com.vtiger.pomRepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sdet34l1.genericUtility.WebDriverUtility;

public class SearchOrganizationContactPage {

	@FindBy(id="search_txt")
	private WebElement searchTxt;
	
	@FindBy(name="search")
	private WebElement searchBtn;
	
//
public 	SearchOrganizationContactPage(WebDriver driver)
{
	PageFactory.initElements(driver, this);
}

//
	public void selectOrganization(WebDriver driver,String organizationName)
	{
		WebDriverUtility.switchToWindowBasedOnTitle(driver,"Organizations");
		searchTxt.sendKeys(organizationName);
		searchBtn.click();
		driver.findElement(By.xpath("//a[contains(@onclick,'set_return_contact_address')]")).click();
	}

}
