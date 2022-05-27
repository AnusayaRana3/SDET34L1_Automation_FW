package com.vtiger.practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DataBaseConnectionTest {
public static void main(String[] args) throws SQLException {
	Driver driver=new Driver();
	DriverManager.registerDriver(driver);
	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/sdet34l1","root","root");
	Statement statement=connection.createStatement();
	ResultSet result=statement.executeQuery("select*from anu;");
	while(result.next())
			{
	
		System.out.println(result.getString("id")+"  "+result.getString("name"));
		
			}
	connection.close();
}
}