package com.sdet34l1.genericUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
/**
 * This Class contains only Java Specific reusable methods
 * @author ANUSAYA RANA
 *
 */
public class JavaClassUtility {
	/**
	 * This method is used to Convert String value to long Datatype
	 * @param value
	 * @return
	 */
public long stringToLong(String value) {
	return Long.parseLong(value);
}
/**
 * This method is used to get the random Number
 * @param limit
 * @return
 */
public int getRandomNumber(int limit) {
	Random ran=new Random();
	return ran.nextInt(limit);
}
/**
 * This method is used to print the message
 * @param message
 */
public void printStatement(String message) {
	System.out.println(message);
}
/**
 * This method is used to assertion through if condition
 * @param actualResult
 * @param expectedResult
 * @param testCaseName
 */
public void assertionThroughIfCondition(String actualResult,String expectedResult,String testCaseName )
{
	if(actualResult.equalsIgnoreCase(expectedResult))
	{
		System.out.println(testCaseName+ " created successfully");
		System.out.println("TC pass");
	}
}

/**
 * 
 * @return
 */
public String getDateTimeInFormat() {
	return new SimpleDateFormat("yyyy_mm_dd_HH_mm_sss").format(new Date());
}
}
