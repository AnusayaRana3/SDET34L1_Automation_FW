package com.vtiger.contactsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.WebDriverUtility;
import com.vtiger.pomRepository.ContactInformationPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;
import com.vtiger.pomRepository.CreateNewOrganizationPage;
import com.vtiger.pomRepository.OrganizationInformationPage;
import com.vtiger.pomRepository.OrganizationPage;
import com.vtiger.pomRepository.SearchOrganizationContactPage;

public class CreateContactsWithOrganizationTest extends BaseClass {

	String organizationName;
	String lastName;
	OrganizationPage organizationPage;
	CreateNewOrganizationPage createNewOrganizationPage;
	OrganizationInformationPage organizationInformationPage;
	ContactPage contactPage;
	CreateNewContactPage createNewContactPage;
	SearchOrganizationContactPage searchOrganizationContactPage;
	ContactInformationPage contactInformationPage;

	@Test(groups="regression")	
	public void createContactWithOrganizationByTestNgTest() {



		organizationName=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 1)+randomnumber;
		javaClassUtility.printStatement(organizationName);

		lastName=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 2)+randomnumber;
		javaClassUtility.printStatement(lastName);

		organizationPage=new OrganizationPage(driver);
		createNewOrganizationPage=new CreateNewOrganizationPage(driver);
		organizationInformationPage=new OrganizationInformationPage(driver);
		contactPage=new ContactPage(driver);
		createNewContactPage=new CreateNewContactPage(driver);
		searchOrganizationContactPage=new SearchOrganizationContactPage(driver);
		contactInformationPage=new ContactInformationPage(driver);

		homePage.clickOrganization(driver);

		organizationPage.clickcreateOrganizationLookUpImg();

		createNewOrganizationPage.enterOrganizationName(organizationName);
		createNewOrganizationPage.saveOrganizationAndIndustryAndType();
		
		webDriverUtility.waitUntilElementVisible(organizationInformationPage.getOrganizationInformationMsgElement());
		javaClassUtility.assertionThroughIfCondition(organizationInformationPage.getOrganizationName(), organizationName,"Organization Created");

		homePage.clickContact(driver);

		contactPage.clickcreateContactLookUpImg();

		createNewContactPage.enterLastNameAndSwitchToSearchOrganization(lastName,driver);

		searchOrganizationContactPage.selectOrganization(driver, organizationName);

		WebDriverUtility.switchToWindowBasedOnTitle(driver,"Contacts");

		createNewContactPage.saveContact();

		javaClassUtility.assertionThroughIfCondition(contactInformationPage.getLastName(), lastName,"Correct Last Name");
	}
}
