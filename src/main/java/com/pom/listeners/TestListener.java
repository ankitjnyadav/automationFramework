package com.pom.listeners;

import java.util.List;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.pom.framework.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.LogStatus;

public class TestListener extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		
		test=extent.startTest(result.getMethod().getMethodName());
		log.info("***** "+result.getMethod().getMethodName()+" Started *****");
		
	}

	public void onTestSuccess(ITestResult result) {
		log.info("***** "+result.getMethod().getMethodName()+" Passed *****");
		test.log(LogStatus.PASS, result.getMethod().getMethodName()+" Passed *****");
		extent.endTest(test);
		
	}

	public void onTestFailure(ITestResult result) {
		
		String screenshot ="data:image/png;base64,"+((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		log.error("Test Failed ",result.getThrowable());
		test.log(LogStatus.FAIL, result.getThrowable().getMessage()+"\n"+test.addBase64ScreenShot(screenshot));
		extent.endTest(test);
	}

	public void onTestSkipped(ITestResult result) {
		log.info("***** "+result.getMethod().getMethodName()+" Skipped *****");
		test.log(LogStatus.PASS, result.getMethod().getMethodName()+" Skipped *****");
		extent.endTest(test);
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		String suiteName=context.getSuite().getName().replace(" ","_");
		extent = new ExtentReports(WORKINGDIR+"\\ExtentReports\\"+suiteName+"_"+System.getProperty("current.date.time")+".html", true);
	}

	
	public void onFinish(ITestContext context) {		
		extent.flush();
		extent.close();
	}
}
