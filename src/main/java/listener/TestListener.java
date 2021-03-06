package listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

import reports.ExtentManager;
import reports.ExtentTestManager;

public class TestListener implements ITestListener {
	public void onStart(ITestContext context) {
		System.out.println("*** Test Suite " + context.getName() + " started ***");
	}

	public void onFinish(ITestContext context) {
		System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
		ExtentTestManager.endTest();
		ExtentManager.getInstance().flush();
	}

	public void onTestStart(ITestResult result) {
		System.out.println(("*** Running test method " + result.getMethod().getMethodName() + "..."));
		ExtentTestManager.startTest(result.getMethod().getMethodName());
	}

	public static String getScreenhot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, we could see a folder "FailedTestsScreenshots" under src
		// folder
		String destination = System.getProperty("user.dir") + "/FailedTestsScreenshots/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}

	public void onTestSuccess(ITestResult result) {
		System.out.println("*** Executed " + result.getMethod().getMethodName() + " test successfully...");
		ExtentTestManager.getTest().log(Status.PASS, "Test passed");
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("***** Error " + result.getName() + " test has failed *****");
		String methodName = result.getName().toString().trim();
		ITestContext context = result.getTestContext();
		WebDriver driver = (WebDriver) context.getAttribute("driver");
		takeScreenShot(methodName, driver, result);
	}

	public void takeScreenShot(String methodName, WebDriver driver, ITestResult result) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String fileSeperator = System.getProperty("file.separator");

		String testMethodName = result.getName().toString().trim();
		String screenShotName = testMethodName + ".png";
		String testClassName = result.getInstanceName().trim();
		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
				+ "screenshots";
		String targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;
		File targetFile = new File(targetLocation);

		// The below method will save the screen shot in d drive with test method name
		try {

			FileUtils.copyFile(scrFile, new File(targetFile + ".png"), true);
			System.out.println("***Placed screen shot in " + targetLocation + " ***");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public void onTestFailure(ITestResult result) {
//		log.info("*** Test execution " + result.getMethod().getMethodName() + " failed...");
//		log.info((result.getMethod().getMethodName() + " failed!"));
//
//		ITestContext context = result.getTestContext();
//		WebDriver driver = (WebDriver) context.getAttribute("driver");
//
//		String targetLocation = null;
//
//		String testClassName = getTestClassName(result.getInstanceName()).trim();
//		String timeStamp = Util.getCurrentTimeStamp(); // get timestamp
//		String testMethodName = result.getName().toString().trim();
//		String screenShotName = testMethodName + timeStamp + ".png";
//		String fileSeperator = System.getProperty("file.separator");
//		String reportsPath = System.getProperty("user.dir") + fileSeperator + "TestReport" + fileSeperator
//				+ "screenshots";
//		log.info("Screen shots reports path - " + reportsPath);
//		try {
//			File file = new File(reportsPath + fileSeperator + testClassName); // Set
//																				// screenshots
//																				// folder
//			if (!file.exists()) {
//				if (file.mkdirs()) {
//					log.info("Directory: " + file.getAbsolutePath() + " is created!");
//				} else {
//					log.info("Failed to create directory: " + file.getAbsolutePath());
//				}
//
//			}
//
//			File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//			targetLocation = reportsPath + fileSeperator + testClassName + fileSeperator + screenShotName;// define
//																											// location
//			File targetFile = new File(targetLocation);
//			log.info("Screen shot file location - " + screenshotFile.getAbsolutePath());
//			log.info("Target File location - " + targetFile.getAbsolutePath());
//			FileHandler.copy(screenshotFile, targetFile);
//
//		} catch (FileNotFoundException e) {
//			log.info("File not found exception occurred while taking screenshot " + e.getMessage());
//		} catch (Exception e) {
//			log.info("An exception occurred while taking screenshot " + e.getCause());
//		}
//
//		// attach screenshots to report
//		try {
//			ExtentTestManager.getTest().fail("Screenshot",
//					MediaEntityBuilder.createScreenCaptureFromPath(targetLocation).build());
//		} catch (IOException e) {
//			log.info("An exception occured while taking screenshot " + e.getCause());
//		}
//		ExtentTestManager.getTest().log(Status.FAIL, "Test Failed");
//	}

	public void onTestSkipped(ITestResult result) {
		System.out.println("*** Test " + result.getMethod().getMethodName() + " skipped...");
		ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println("*** Test failed but within percentage % " + result.getMethod().getMethodName());
	}

}
