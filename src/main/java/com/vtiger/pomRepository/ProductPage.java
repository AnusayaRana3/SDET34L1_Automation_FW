package com.vtiger.pomRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {

	@FindBy(xpath="//img[@title='Create Product...']")
	private WebElement createProductLookUpImg;
	
//Constructor initialize		
	public ProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver,this);
		
	}
	
//business library	
	public void clickcreateProductLookUpImg() 
	{
		createProductLookUpImg.click();
		
	}
}
