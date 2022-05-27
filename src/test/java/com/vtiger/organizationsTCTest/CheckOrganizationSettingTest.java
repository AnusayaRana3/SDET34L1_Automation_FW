package com.vtiger.organizationsTCTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.OrganizationPage;
import com.vtiger.pomRepository.OrganizationSettingPage;

public class CheckOrganizationSettingTest extends BaseClass {
	String organizationname;
	OrganizationPage organizationPage;
	OrganizationSettingPage organizationSettingPage;
	
	@Test
	public void checkOrganizationSettingByTestNgTest() {
		
		organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;

		organizationPage=new OrganizationPage(driver);
		organizationSettingPage=new OrganizationSettingPage(driver);
		
		homePage.clickOrganization(driver);
		organizationPage.clickOrganizationSettinLookupImg();
		javaClassUtility.printStatement(organizationSettingPage.getOrganizationSettingPageMsg());
	
	}

}
