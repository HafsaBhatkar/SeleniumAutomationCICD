package companyName.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import companyName.resources.ExtentReporterTestNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReporterTestNG.getReporterObject();
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

	@Override
	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test passed successfully: " + result.getName());
		System.out.println("Test passed successfully: " + result.getName());
		// Screenshot
//		try {
//			String filePath = getScreenshot(result.getMethod().getMethodName());
//			test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Test passed successfully: " + result.getName());
		extentTest.get().log(Status.FAIL, "Test Failed");
		extentTest.get().fail(result.getThrowable());

		// Screenshot
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
			String filePath = getScreenshot(result.getMethod().getMethodName(), driver);
			extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Screenshot
//		try {
//			String filePath = getScreenshot(result.getMethod().getMethodName());
//			test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// Code to run when a test is skipped
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// Code to run when a test fails but stays inside its success rate
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// Code to run when a test fails because it took too much time
	}

	@Override
	public void onStart(ITestContext context) {
		// Code to run before any test in the tag starts
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
