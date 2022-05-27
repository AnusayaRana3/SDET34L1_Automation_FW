package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductInformationPage {

	@FindBy(xpath="//span[@id='dtlview_Product Name']")
	private WebElement productNameText;
	
	
//
public ProductInformationPage(WebDriver driver)	
{
	PageFactory.initElements(driver, this);
}

//
	public String getproductNameText()
	{
		return productNameText.getText();
	}
}
