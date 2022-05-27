package com.vtiger.campaignTest;

import org.testng.annotations.Test;
import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.CampaignInformationPage;
import com.vtiger.pomRepository.CampaignPage;
import com.vtiger.pomRepository.CreateNewCampaignPage;

public class CreateCampaignTest extends BaseClass {
	
	String campaignname;
	CampaignPage campaignPage;
	CreateNewCampaignPage createCampaignPage;
	CampaignInformationPage campaignInformationPage;
	
	@Test(groups="sanity")
	public void createCampaignByTestNgTest() {
	
		campaignname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;
		
		campaignPage=new CampaignPage(driver);
		createCampaignPage=new CreateNewCampaignPage(driver);
		campaignInformationPage=new CampaignInformationPage(driver);
		
		homePage.clickCampaign(driver, webDriverUtility);
		campaignPage.clickcreateCampaignLookUpImg();
		createCampaignPage.enterCampaignNameAndEnterAndSwitchToSearchProduct(campaignname,driver);
		createCampaignPage.saveCampaign();
		
		javaClassUtility.assertionThroughIfCondition(campaignInformationPage.getCampaignName(), campaignname,"campaign");
		
		ExcelCommonClassUtility.setDataInExcel("Contacts", 17 ,5,"create contact page displayed");
		ExcelCommonClassUtility.setDataInExcel("Contacts", 17 ,6,"pass");
		
}
}
