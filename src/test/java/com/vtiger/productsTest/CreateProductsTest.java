package com.vtiger.productsTest;

import org.testng.annotations.Test;
import com.sdet34l1.genericUtility.BaseClass;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.vtiger.pomRepository.CreateNewProductPage;
import com.vtiger.pomRepository.ProductInformationPage;
import com.vtiger.pomRepository.ProductPage;

public class CreateProductsTest extends BaseClass {

	String productactual;
	ProductPage productPage;
	CreateNewProductPage createNewProductPage;
	ProductInformationPage productInformationPage;
	
	@Test(groups="regression")	
	public void createProductsFromPropertiesByTestNgTest() {

		productactual=ExcelCommonClassUtility.getDataFromExcel("Contacts", 8, 2)+randomnumber;

		productPage=new ProductPage(driver);
		createNewProductPage=new CreateNewProductPage(driver);
		productInformationPage=new ProductInformationPage(driver);

		homePage.clickProduct(driver);

		productPage.clickcreateProductLookUpImg();

		createNewProductPage.enterProductNameAndSave(productactual);

		javaClassUtility.assertionThroughIfCondition(productInformationPage.getproductNameText(), productactual,"correct product name");
		
	}
}
