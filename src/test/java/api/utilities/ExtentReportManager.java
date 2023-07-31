package api.utilities;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.sl.usermodel.ObjectMetaData.Application;
import org.mozilla.javascript.ast.NewExpression;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

//import java.utils.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import api.endpoints.UserEndPoints;

public class ExtentReportManager implements ITestListener{
	
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		String repName;
	
		public void onStart(ITestContext testContext)
		{
			String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			repName="Test-Report-"+timeStamp+".html";
			
		
					sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
					
					sparkReporter.config().setDocumentTitle("RestAssuredAutomationProject");
					sparkReporter.config().setReportName("Pet store users API");
					sparkReporter.config().setTheme(Theme.DARK);
					
					extent=new ExtentReports();
					extent.attachReporter(sparkReporter);
					extent.setSystemInfo("Application", "Pet store users API");
					extent.setSystemInfo("Operating", System.getProperty("OS.name"));
					extent.setSystemInfo("User Name", System.getProperty("user.name"));
					extent.setSystemInfo("Environment"," QA");
					extent.setSystemInfo("user", "shweta");
		}
					
		    //@Override
			public void onTestSuccess(ITestResult Result)
			{
				test=extent.createTest(Result.getName());
				test.createNode(Result.getName());
				test.assignCategory(Result.getMethod().getGroups());
				test.log(Status.PASS, "Test Passed");
				test.log(Status.PASS, Result.getThrowable().getMessage());		
			}


			public void onTestFailure(ITestResult Result)
			{
				test=extent.createTest(Result.getName());
				test.createNode(Result.getName());
				test.assignCategory(Result.getMethod().getGroups());
				test.log(Status.FAIL, "Test Failed");
				test.log(Status.FAIL, Result.getThrowable().getMessage());		
			}
				
			
			public void onTestSkipped(ITestResult Result)
			{
				test=extent.createTest(Result.getName());
				test.createNode(Result.getName());
				test.assignCategory(Result.getMethod().getGroups());
				test.log(Status.SKIP, "Test Skipped");
				test.log(Status.SKIP, Result.getThrowable().getMessage());		
			}
			
			public void onFinish(ITestContext Result)
			{
				extent.flush();
			}					
}
