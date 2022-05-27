package com.vtiger.practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class FetchDataFromExcelWIthDataFormatter {
public static void main(String[] args) throws EncryptedDocumentException, IOException {
	FileInputStream fis=new FileInputStream("./src/test/resources/excel.xlsx");
	Workbook wb=WorkbookFactory.create(fis);
	Sheet sh=wb.getSheet("Contacts");
	Row row=sh.getRow(10);
	Cell cell=row.getCell(3);
	
	//by Using DataFormatter
	DataFormatter df=new DataFormatter();
	String Data=df.formatCellValue(cell);
//	
//	String data=cell.toString();
//	String data1=cell.getStringCellValue();
//	Double data2=cell.getNumericCellValue();
	
	System.out.println(Data);
	wb.close();
}
}
