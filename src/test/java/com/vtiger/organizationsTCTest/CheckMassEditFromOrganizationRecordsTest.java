package com.vtiger.organizationsTCTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.OrganizationMassEditRecordsPage;
import com.vtiger.pomRepository.OrganizationPage;

public class CheckMassEditFromOrganizationRecordsTest extends BaseClass {

	String organizationname;
	OrganizationPage organizationPage;
	OrganizationMassEditRecordsPage organizationMassEditRecordsPage;

	@Test
	public void checkMassEditFromOrganizationRecordsTest() {

		organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;

		organizationPage=new OrganizationPage(driver);
		organizationMassEditRecordsPage=new OrganizationMassEditRecordsPage(driver);
		
		homePage.clickOrganization(driver);

		organizationPage.clickShowingOrgNoCheCkBox();
		organizationPage.clickMassEditTxt();
	
		javaClassUtility.printStatement(organizationMassEditRecordsPage.getOrganizationMassEditRecordsPageMsg());

	}
}
