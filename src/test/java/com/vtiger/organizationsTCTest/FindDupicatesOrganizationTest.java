package com.vtiger.organizationsTCTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.MergingCriteriaSelectionAvailableFeildsPage;
import com.vtiger.pomRepository.OrganizationInformationPage;
import com.vtiger.pomRepository.OrganizationPage;

public class FindDupicatesOrganizationTest extends BaseClass {

	String organizationname;
	OrganizationPage organizationPage;
	MergingCriteriaSelectionAvailableFeildsPage mergingCriteriaSelection;
	OrganizationInformationPage organizationInformationPage;
	
	@Test
	public void findDupicatesOrganizationByTestNgTest() {
		
		organizationname=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 1)+randomnumber;

		organizationPage=new OrganizationPage(driver);
		mergingCriteriaSelection=new MergingCriteriaSelectionAvailableFeildsPage(driver);

		homePage.clickOrganization(driver);

		organizationPage.clickFindDuplicatesLookUpImg();
		
		//webDriverUtility.waitUntilElementVisible(organizationInformationPage.getOrganizationInformationMsgElement());
		mergingCriteriaSelection.selectOrganizationNameTabFromAvailableFields();
		
		mergingCriteriaSelection.selectleftToRightArrowBtn();
	
		mergingCriteriaSelection.clickfindDuplicatesBtn();

		javaClassUtility.printStatement(homePage.getnoDuplicateRecordsMsg());
		
	}
}
