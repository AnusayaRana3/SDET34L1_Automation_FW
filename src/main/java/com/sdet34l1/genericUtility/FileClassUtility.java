package com.sdet34l1.genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This class contains only property file specific common methods
 * @author ANUSAYA RANA
 *
 */
public class FileClassUtility {
static Properties property;
/**
 * This method is to open the property file
 * @param filepath
 * @throws IOException
 */
public static void openPropertyFile(String PROPERTYFILEPATH) throws IOException {
	FileInputStream fis=new FileInputStream(PROPERTYFILEPATH);
	property=new Properties();
	property.load(fis);
}
/**
 * This method is used to fetch the data from the Property File
 * @param key
 * @return
 */
public static String getDataFromPropertyFile(String key) {
	String value=property.getProperty(key);
	return value;
}
}
