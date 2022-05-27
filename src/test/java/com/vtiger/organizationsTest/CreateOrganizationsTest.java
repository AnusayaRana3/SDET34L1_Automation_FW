package com.vtiger.organizationsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.OrganizationInformationPage;
import com.vtiger.pomRepository.OrganizationPage;

public class CreateOrganizationsTest extends BaseClass {

	String organizationname;
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	
	@Test
	public void createOrganizationByTestNgTest() {

		organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;

		organizationPage=new OrganizationPage(driver);
		createNewOrganizationPage=new CreateNewOrganizationPage(driver);
		organizationInformationPage=new OrganizationInformationPage(driver);
	
		homePage.clickOrganization(driver);
		organizationPage.clickcreateOrganizationLookUpImg();
		createNewOrganizationPage.enterOrganizationName(organizationname);
		createNewOrganizationPage.saveOrganizationAndIndustryAndType();
		webDriverUtility.waitUntilElementVisible(organizationInformationPage.getOrganizationInformationMsgElement());
		javaClassUtility.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), organizationname,"Correct Organization Name Created");
	}
}

