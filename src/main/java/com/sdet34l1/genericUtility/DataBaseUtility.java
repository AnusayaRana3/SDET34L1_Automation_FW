package com.sdet34l1.genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.jdbc.Driver;

/**
 * This Class is used to get/set the data from database
 * @author ANUSAYA RANA
 *
 */
public class DataBaseUtility {

	static Driver driver;
	static Connection connection;
	static Statement statement;
	
	/**
	 * This method is used to open the data from database and get connection
	 * @param dbUrl
	 * @param dbUserName
	 * @param dbPassword
	 * @throws SQLException
	 */
	public static void openDataFromDatabaseConnection(String dbUrl,String dbUserName,String dbPassword) throws SQLException
	{
		driver=new Driver();
		DriverManager.registerDriver(driver);
		connection=DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		statement=connection.createStatement();
	}
	
	/**
	 * This method is used to close the Database
	 */
	public static void closeDataBase()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is used to get the data from database
	 * @param query
	 * @param columnName
	 * @return
	 */
	public static ArrayList<String> getDataFromDataBase(String query,String columnName)
	{
		ArrayList<String> list=new ArrayList<String>();
		ResultSet result = null;
		try {
			result = statement.executeQuery(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			while(result.next())
			{
				try {
					list.add(result.getString(columnName));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * This method is used to set the data into Database
	 * @param inputQuery
	 * @throws SQLException 
	 */
	public static void setDataIntoDataBase(String inputQuery) throws SQLException
	{
		int result=statement.executeUpdate(inputQuery);
		if(result>=1)
		{
			System.out.println("data insert in database successfully");
		}
	}
	
	/**
	 * This method is used to validate the data in Database
	 * @param query
	 * @param columnName
	 * @param expectedData
	 * @return
	 */
	public static boolean validateDataInDataBase(String query,String columnName,String expectedData)
	{
		ArrayList<String> list=getDataFromDataBase(query,columnName);
		boolean flag=false;
		for(String actualData:list)
		{
			if(actualData.equalsIgnoreCase(expectedData))
			{
				flag = true;
				System.out.println("Tc pass");
				break;
			}
		}
		return flag;
	}
}
