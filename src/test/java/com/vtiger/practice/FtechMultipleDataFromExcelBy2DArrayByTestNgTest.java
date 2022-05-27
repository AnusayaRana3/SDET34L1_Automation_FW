package com.vtiger.practice;

import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import com.sdet34l1.genericUtility.ExcelCommonClassUtility;
import com.sdet34l1.genericUtility.InterfaceCPathUtility;

public class FtechMultipleDataFromExcelBy2DArrayByTestNgTest {
	
	@Test(dataProvider="loginData")
	public void getMultipleDataFromExcelBy2DArrayByTestNgTest(String username,String password )
	{
		Reporter.log(username+" -----> "+password,true);
	}
	@DataProvider
	public Object[][] loginData() throws EncryptedDocumentException, IOException
	{
			ExcelCommonClassUtility.openExcel(InterfaceCPathUtility.EXCELFILEPATH);
		return ExcelCommonClassUtility.getMultipleDataFromExcel("loginData");
	}
}
