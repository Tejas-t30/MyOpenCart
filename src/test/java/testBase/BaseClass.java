package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties properties;
	
	@BeforeClass(groups= {"Master","Registration","Login"})
	@Parameters ({"browser","os"})
	public void openBrowser(String br, String os) throws IOException
	{
		logger=LogManager.getLogger(this.getClass());
		FileReader file=new FileReader("./src//test//resources//config.properties");
		properties=new Properties();
		properties.load(file);
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities= new DesiredCapabilities();
			//For OS
			if(os.equalsIgnoreCase("Windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("Mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux"))
			{
				capabilities.setPlatform(Platform.LINUX);
			}
			else
			{
				System.out.println("Invalid os name");
				return;
			}
			
			//for browser
			switch(br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome");
				break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox": capabilities.setBrowserName("firefox");
				break;
			default: System.out.println("Invalid browser name");
				return;
			
			}
			
			//to launch browser
			driver=new RemoteWebDriver(new URL("http://192.168.1.52:4444/wd/hub"),capabilities);
		}
		
		
		if(properties.getProperty("execution_env").equalsIgnoreCase("local"))
		{
			switch(br.toLowerCase())
			{
			case "chrome": driver=new ChromeDriver(); break;
			case "edge": driver=new EdgeDriver(); break;
			default: System.out.println("Invalid browser"); return;
			}
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(properties.getProperty("url"));
	}
	@AfterClass(groups= {"Master","Registration","Login"})
	public void tearDown()
	{
		driver.quit();
	}
	
	public String randomAlphabetString()
	{
		String generatedString=RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}
	public String randomNumberString()
	{
		String generatedString=RandomStringUtils.randomNumeric(10);
		return generatedString;
	}
	public String randomAlphaNumericString()
	{
		String generatedAlphabet=RandomStringUtils.randomAlphabetic(5);
		String generatedNumber=RandomStringUtils.randomNumeric(10);
		return (generatedAlphabet+"@"+generatedNumber);
	}
	public String captureScreenshot(String testname) throws Exception
	{
		SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String timeStamp=df.format(dt);
		
		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File sourceFile=screenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+ timeStamp +".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
		
		return targetFilePath;
		
	}

}
