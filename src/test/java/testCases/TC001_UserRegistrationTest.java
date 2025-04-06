package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC001_UserRegistrationTest extends BaseClass{
	@Test(groups= {"Master","Registration"})
	public void userRegistration()
	{
		logger.info("***String TC001_UserRegistrationTest***");
		try
		{
		HomePage hp=new HomePage(driver);
		hp.myAccountClick();
		hp.registerClick();
		logger.info("Clicked on Register Link...");
		
		RegisterPage rp=new RegisterPage(driver);
		rp.firstName(randomAlphabetString());
		rp.LastName(randomAlphabetString());
		rp.email(randomAlphabetString()+"@gmail.com");
		rp.phoneNumber(randomNumberString());
		String password=randomAlphaNumericString();
		rp.password(password);
		rp.confirmPassword(password);
		rp.agree();
		rp.continueButton();
		logger.info("Filled the Register form...");
		
		Assert.assertEquals(rp.confirmationMsg(), "Your Account Has Been Created!");
		logger.info("Test Case Passed and Account is registered");
		}
		catch(Exception e)
		{
			logger.error("Test failed: " + e.getMessage());
			Assert.fail("Test failed: " + e.getMessage());

		}
		finally
		{
			logger.info("Finished the TestCase TC001_UserRegistrationTests");
		}
		
	}

	
	

}
