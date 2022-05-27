package com.vtiger.contactsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.ContactInformationPage;
import com.vtiger.pomRepository.ContactPage;
import com.vtiger.pomRepository.CreateNewContactPage;

public class CreateContactsTest extends BaseClass {

	String value;
	ContactPage contactPage;
	CreateNewContactPage createNewContactPage;
	ContactInformationPage contactInformationPage;

	@Test(groups="sanity")
	
	
	public void createContactWithExternalExcelByTestNgTest() {

		value=ExcelCommonClassUtility.getDataFromExcel("Contacts", 5, 1)+randomnumber;
		javaClassUtility.printStatement(value);

		contactPage=new ContactPage(driver);
		createNewContactPage=new CreateNewContactPage(driver);
		contactInformationPage=new ContactInformationPage(driver);
		
		homePage.clickContact(driver);
		contactPage.clickcreateContactLookUpImg();
		createNewContactPage.enterLastNameAndSaveContact(value);
		javaClassUtility.assertionThroughIfCondition(contactInformationPage.getLastName(), value,"Contacts Lastname");
	}
}
