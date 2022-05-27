package com.sdet34l1.genericUtility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzerImplementation implements IRetryAnalyzer {
	
	int count=0;
	int maxRetry=4;
	@Override
	public boolean retry(ITestResult result) 
	{
	while(count<maxRetry)
	{
		count++;
		return true;
	}
	return false;
	}

}
