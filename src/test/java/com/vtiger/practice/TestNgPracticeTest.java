package com.vtiger.practice;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgPracticeTest {
@Test
public void practiceTest()
{
	//it will print in Console only
	System.out.println("Welcome to testNg");
	//it will print in Report only
	Reporter.log("Welcome to testNg in report");
	//it will print in both console & report
	Reporter.log("Welcome to testNg in report1",true);
	//it will print in report only
	Reporter.log("Welcome to testNg in report",false);
}

@Test
public void practiceTest1()
{
	//Initially fail testcase
	Assert.fail();
	Reporter.log("Welcome to testNg in report with fail");
}
}