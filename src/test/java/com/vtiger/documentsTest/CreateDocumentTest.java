package com.vtiger.documentsTest;

import org.testng.annotations.Test;

import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.CreateNewDocumentPage;
import com.vtiger.pomRepository.DocumentInformationPage;
import com.vtiger.pomRepository.DocumentPage;

public class CreateDocumentTest extends BaseClass {

	String title;
	String documentpath;
	String documentDescription;
	DocumentPage documentPage;
	CreateNewDocumentPage createNewDocumentPage;
	DocumentInformationPage documentInformationPage;

	@Test(groups="regression")	
	public void createDocumentWithIframByTestNgTest() {


		title=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 1)+randomnumber;
		documentpath=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 2);
		documentDescription=ExcelCommonClassUtility.getDataFromExcel("Documents", 2, 3);

		documentPage=new DocumentPage(driver);
		createNewDocumentPage=new CreateNewDocumentPage(driver);
		documentInformationPage=new DocumentInformationPage(driver);

		homePage.clickDocument(driver);

		documentPage.clickcreateDocumentLookUpImg();

		createNewDocumentPage.enterTitleName(title);


		createNewDocumentPage.switchToFrameAndDescriptionSelectAllAndSwitchToMainFrame(driver, documentDescription);

		createNewDocumentPage.clickBoldLetter();

		createNewDocumentPage.clickItalicLetter();

		createNewDocumentPage.choosenFileOption(documentpath);

		createNewDocumentPage.saveBt();

		javaClassUtility.assertionThroughIfCondition(documentInformationPage.getTitleName(), title,"Correct Document Title created successfully");

		ExcelCommonClassUtility.setDataInExcel("Documents", 2 ,4,"Documents page is Displayed");
		ExcelCommonClassUtility.setDataInExcel("Contacts", 2 ,5,"pass");
	}
}
