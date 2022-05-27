package com.sdet34l1.genericUtility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * This class is used to maintain all the excel specific common methods
 * @author ANUSAYA RANA
 *
 */
public class ExcelCommonClassUtility 
{
	static Workbook wb;
	/**
	 * This method is used to open the Excelsheet file
	 * @param filepath
	 * @throws EncryptedDocumentException
	 * @throws IOException
	 */
	public static void openExcel(String EXCELFILEPATH) throws EncryptedDocumentException, IOException
	{
		FileInputStream fisexcel=new FileInputStream(EXCELFILEPATH);
		wb =WorkbookFactory.create(fisexcel);
	}
	/**
	 * This method is used to fetch the data from the excel sheet
	 * @param SheetName
	 * @param rowNumber
	 * @param cellNumber
	 * @return
	 */
	public static String getDataFromExcel(String SheetName,int rowNumber,int cellNumber)
	{
		String data=wb.getSheet(SheetName).getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return data;
	}
	
	/**
	 * 
	 * @return
	 */
	public static Object[][] getMultipleDataFromExcel(String sheetName)
	{
	Sheet sh=wb.getSheet(sheetName);
	
	Object[][] arr=new Object[sh.getLastRowNum()+1][sh.getRow(0).getLastCellNum()];
	for(int i=0; i<sh.getLastRowNum()+1; i++)
	{
		for(int j=0; j<sh.getRow(i).getLastCellNum(); j++)
		{
			arr[i][j]=sh.getRow(i).getCell(j).getStringCellValue();
		}
	}
	return arr;
}
	
	/**
	 * 
	 * @param EXCELWRITEPATH
	 * @throws IOException
	 */
	public static void writeExcel(String EXCELWRITEPATH) 
	{
		FileOutputStream fosexcel = null;
		try {
			fosexcel = new FileOutputStream(EXCELWRITEPATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb.write(fosexcel);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This method is used to set the message into Excel
	 * @param SheetName
	 * @param rowNumber
	 * @param cellNumber
	 */
	public static void setDataInExcel(String SheetName,int rowNumber,int cellNumber,String message ) 
	{
		wb.getSheet(SheetName).getRow(rowNumber).createCell(cellNumber).setCellValue(message);
		//wb.getSheet(SheetName).getRow(rowNumber).createCell(cellNumber).setCellValue("pass");
		
	}
	/**
	 * This method is used to close the Excel connection
	 */
	public static void closeExcel()
	{
		try {
			wb.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("while closing excel some exception occured");
		}

	}
}
