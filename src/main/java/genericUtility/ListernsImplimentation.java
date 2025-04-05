package genericUtility;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListernsImplimentation implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	@Override
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "---Started");
		test = report.createTest(methodName);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "---Passed");
		test.log(Status.PASS, methodName + " -----Passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "---Failed");
		test.log(Status.FAIL, methodName + " -----Failed");
		test.log(Status.INFO, result.getThrowable());

		WebDriverUtility wutil = new WebDriverUtility();
		JavaUtility jutil = new JavaUtility();
		String screenshotName = methodName + "-" + jutil.toGetSystemDateAndTime();
		try {
			String RecievedPath = wutil.toTakeScreenshot(BaseClass.sdriver, screenshotName); // sdriver(driver) is declared in base class
			test.addScreenCaptureFromPath(RecievedPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "---Skipped");
		test.log(Status.SKIP, methodName + " -----Skipped");
		test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onStart(ITestContext context) {
		System.out.println("---Suite Execution Started---");

		// ExtentReports
		ExtentSparkReporter htmlReports = new ExtentSparkReporter(
				"./ExtentReports" + new JavaUtility().toGetSystemDateAndTime() + ".html");
		htmlReports.config().setDocumentTitle("Vtiger Execution Report");
		htmlReports.config().setTheme(Theme.DARK);
		htmlReports.config().setReportName("VTIGER EXECUTION REPORT");

		report = new ExtentReports();
		report.attachReporter(htmlReports);
		report.setSystemInfo("Base URL", "https://localhost:8888");
		report.setSystemInfo("UserName", "admin");
		report.setSystemInfo("Password", "password");
		report.setSystemInfo("ReporterName", "Punith Raj Kumar");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("---Suite Execution Finised---");
		report.flush();
	}

}
