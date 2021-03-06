package com.sdet34l1.genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListenerImplementation implements ITestListener {

	ExtentReports report;
	ExtentTest test;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("onStart");
		ExtentSparkReporter spark=new ExtentSparkReporter("./extentReport/extentreport.html");
		spark.config().setDocumentTitle("Documents Title");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Anu Report");
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("Enviornment","Testing Enviornment");
		report.setSystemInfo("Reporter name","Anusaya Rana");
		report.setSystemInfo("Platform","windows 10");
		report.setSystemInfo("Unit Testing Tool","TestNg");
		report.setSystemInfo("Automation Tool","Selenium");
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("onTestStart");
		
		test=report.createTest(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("onTestSuccess");
		
		//test=report.createTest(result.getMethod().getMethodName()+" is pass");
		test.log(Status.PASS,result.getMethod().getMethodName()+" is passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("onTestFailure");
		
		test.log(Status.FAIL,result.getMethod().getMethodName()+" is failed");
		test.log(Status.FAIL,result.getThrowable());
		
		try {
			test.addScreenCaptureFromPath(WebDriverUtility.takeScreenshot(result.getMethod().getMethodName(),BaseClass.staticDriver));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println("onTestSkipped");
		

		test.log(Status.SKIP,result.getMethod().getMethodName()+" is skipped");
		test.log(Status.SKIP,result.getThrowable());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("onFinish");
		
		report.flush();
		
	}

	
	
	
}
