package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC004_UserLoginTest extends BaseClass{
	@Test(groups= {"Master","Login"})
	public void userLogin()
	{
		logger.info("***Starting TC004_UserLoginTest***");
		try {
		HomePage hp=new HomePage(driver);
		hp.myAccountClick();
		hp.loginClick();
		
		LoginPage lp=new LoginPage(driver);
		lp.enterEmail(properties.getProperty("email"));
		lp.enterPassword(properties.getProperty("password"));
		lp.clickLogin();
		
		MyAccountPage mp=new MyAccountPage(driver);
		boolean targetPage=mp.ismyAccountTxtDisplayed();
		Assert.assertEquals(targetPage, true);
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		finally
		{
			logger.info("Finished test TC004_UserLoginTest");
		}
	}

}
