package com.vtiger.campaignTest;

import org.testng.annotations.Test;
import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.ProductPage;
import com.vtiger.pomRepository.SearchCampaignProductPage;

public class CreateCampaignWithProductsTest extends BaseClass {
	
	String productname;
	String campaignname;
	CampaignPage campaignPage;
	CreateNewCampaignPage createCampaignPage;
	CampaignInformationPage campaignInformationPage;
	ProductPage productPage;
	CreateNewProductPage createNewProductPage;
	SearchCampaignProductPage searchCampaignProductPage;
	
	@Test(groups="regression")
	public void createCampaignWithProductsByTest() {

		productname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 2)+randomnumber;
		javaClassUtility.printStatement(productname);
		campaignname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
		javaClassUtility.printStatement(campaignname);
			
		campaignPage=new CampaignPage(driver);
		createCampaignPage=new CreateNewCampaignPage(driver);
		campaignInformationPage=new CampaignInformationPage(driver);
		productPage=new ProductPage(driver);
		createNewProductPage=new CreateNewProductPage(driver);
		searchCampaignProductPage=new SearchCampaignProductPage(driver);
			
		homePage.clickProduct(driver);
			
		productPage.clickcreateProductLookUpImg();
			
		createNewProductPage.enterProductNameAndSave(productname);
			

		WebDriverUtility.waitUntilElementClickable(homePage.getMoreDropdown(driver));
			
		homePage.clickCampaign(driver,webDriverUtility);
			
		campaignPage.clickcreateCampaignLookUpImg();
			
		createCampaignPage.enterCampaignNameAndEnterAndSwitchToSearchProduct(campaignname,driver);
		searchCampaignProductPage.selectProduct(productname, driver);		
		WebDriverUtility.switchToWindowBasedOnTitle(driver,"Campaigns");
		
		createCampaignPage.saveCampaign();	
			
		javaClassUtility.assertionThroughIfCondition(campaignInformationPage.getCampaignName(), campaignname,"campaign with product");
		javaClassUtility.assertionThroughIfCondition(campaignInformationPage.getProductName(), productname,"correct product name");
			
		
}
}

