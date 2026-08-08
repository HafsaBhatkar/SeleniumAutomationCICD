package companyName.resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterTestNG {

	public ExtentReporterTestNG() {
		// TODO Auto-generated constructor stub
	}
	
	static ExtentReports extent;
	public static ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Ecommerce Website Testing");
		reporter.config().setDocumentTitle("Test Result");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Hafsa Bhatkar");
		
		return extent;
	}

}
