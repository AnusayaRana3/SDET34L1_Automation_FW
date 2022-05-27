package com.vtiger.organizationsTCTest;

import org.testng.annotations.Test;
import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.OrganizationInformationPage;
import com.vtiger.pomRepository.OrganizationPage;

public class TcSearchOrganizationsByAssignedToTest extends BaseClass {

	String organizationname;
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationpage;
	OrganizationInformationPage organizationInformationPage;
	
	@Test
	public void tcSearchOrganizationsByAssignedToByTestNgTest() {
		
		organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;

		organizationPage=new OrganizationPage(driver);
		createNewOrganizationpage=new CreateNewOrganizationPage(driver);
		organizationInformationPage=new OrganizationInformationPage(driver);

		homePage.clickOrganization(driver);

		organizationPage.clickcreateOrganizationLookUpImg();

		createNewOrganizationpage.enterOrganizationName(organizationname);

		createNewOrganizationpage.goToAssignedToAndClickGroup();

		createNewOrganizationpage.saveOrganizationAndIndustryAndType();
		webDriverUtility.waitUntilElementVisible(organizationInformationPage.getOrganizationInformationMsgElement());
		javaClassUtility.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), organizationname,"Correct Organization Name");

		homePage.clickOrganization(driver);

		organizationPage.enterAnyNameSearchForTxtBox(organizationname);

		organizationPage.clickSearchInAndAssignedToSelectDropdown(driver);

		organizationPage.clickSearchNowBtn();
	}
}

