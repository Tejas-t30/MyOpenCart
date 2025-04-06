package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports report;
	public ExtentTest test;
	String reportName;
	
		public void onStart(ITestContext testContext)
		{
			SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date dt=new Date();
			String currentDateTimeStamp=df.format(dt);
			reportName="Test-Report" + currentDateTimeStamp+".html";
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+reportName);
			
			sparkReporter.config().setDocumentTitle("OpenCart Automation Report");
			sparkReporter.config().setReportName("OpenCart Functional Testing");
			sparkReporter.config().setTheme(Theme.DARK);
			
			report = new ExtentReports();
			report.attachReporter(sparkReporter);
			report.setSystemInfo("Application", "OpenCart");
			report.setSystemInfo("User Name", System.getProperty("user.name"));
			report.setSystemInfo("Enviroment","QA");
			
			String os=testContext.getCurrentXmlTest().getParameter("os");
			report.setSystemInfo("Operating System", os);
			
			String browser=testContext.getCurrentXmlTest().getParameter("browser");
			report.setSystemInfo("Browser", browser);
			
			List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty())
			{
				report.setSystemInfo("Groups", includedGroups.toString());
			}
			
		}
		
		public void onTestSuccess(ITestResult result)
		{
			test=report.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.PASS,result.getName()+" got successfully executed");
		}
		public void onTestFailure(ITestResult result)
		{
			test=report.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.FAIL, result.getName()+" got failed");
			test.log(Status.FAIL,result.getThrowable().getMessage());
			
			try {
				 BaseClass bc=new BaseClass();
				 String imgPath=bc.captureScreenshot(result.getName());
				 test.addScreenCaptureFromPath(imgPath);
				
				 
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		public void onTestSkipped(ITestResult result)
		{
			test=report.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP,result.getName()+" got skipped");
			test.log(Status.SKIP,result.getThrowable().getMessage());
		}
		
		public void onFinish(ITestContext testContext)
		{
			report.flush();
			
			//To open the report file in browser once execution is completed automatically 
			String pathOfExtentReport= System.getProperty("user.dir")+"\\reports\\"+reportName;
			File extentReport=new File(pathOfExtentReport);
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}
