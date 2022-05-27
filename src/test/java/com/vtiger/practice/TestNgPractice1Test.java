package com.vtiger.practice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class TestNgPractice1Test extends TestNgBasicConfigurAnnotationPracticeTest {

	@Test
	public void practice1Test()
	{
		Reporter.log("TestNgPractice1Test-->Test1",true);
	}
	
	@Test
	public void practice2Test()
	{
		Reporter.log("TestNgPractice1Test-->Test2",true);
	}
	
	@Test
	public void practice3Test()
	 {
		Reporter.log("TestNgPractice1Test-->Test3",true);
	 }
	
	@Test
	public void practice4Test()
	 {
		Reporter.log("TestNgPractice1Test-->Test4",true);
	 }
	
	@Test
	public void practice5Test()
	 {
		Reporter.log("TestNgPractice1Test-->Test5",true);
	 }
	
	
}
