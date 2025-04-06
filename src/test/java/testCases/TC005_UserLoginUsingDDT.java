package testCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;


public class TC005_UserLoginUsingDDT extends BaseClass {
	@Test(dataProvider="LoginData" , dataProviderClass=DataProviders.class, groups= {"Master","Login"})
	public void userLoginUsingDataDriven(String email, String pswd, String exp) throws IOException
	{
		logger.info("***Strating TC_005_UserLoginUsingDDT***");
		try {
			HomePage hp=new HomePage(driver);
			hp.myAccountClick();
			hp.loginClick();
			
			LoginPage lp=new LoginPage(driver);
			lp.enterEmail(email);
			lp.enterPassword(pswd);
			lp.clickLogin();
			
			MyAccountPage mp=new MyAccountPage(driver);
			boolean targetPage=mp.ismyAccountTxtDisplayed();
			
			
			if(exp.equalsIgnoreCase("Valid"))
			{
				if(targetPage==true)
				{
					mp.logoutClick();
					Assert.assertTrue(true);
				}
				else
				{
					Assert.assertTrue(false);
				}
			}
			
			if(exp.equalsIgnoreCase("Invalid"))
			{
				if(targetPage==false)
				{
					Assert.assertTrue(true);
				}
				else
				{
					mp.logoutClick();
					Assert.assertTrue(false);
				}
			}

		}
		catch(Exception e)
		{
			Assert.fail("Test Case failed with an error "+ e.getMessage());
		}
		finally
		{
			logger.info("Finished the test TC_005_UserLoginUsingDDT");
		}
	}

}
