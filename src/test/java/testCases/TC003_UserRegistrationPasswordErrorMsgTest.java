package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC003_UserRegistrationPasswordErrorMsgTest extends BaseClass {
	@Test(groups= {"Master","Registration"})
	public void paswordErrorMsg()
	{
		logger.info("***Starting TC003_UserRegistrationPasswordErrorMsgTest***");
		try {
		HomePage hp=new HomePage(driver);
		hp.myAccountClick();
		hp.registerClick();
		logger.info("Clicked on the Register link");
		
		RegisterPage rp=new RegisterPage(driver);
		rp.firstName(randomAlphabetString());
		rp.LastName(randomAlphabetString());
		rp.email(randomAlphabetString()+"@gmail.com");
		rp.phoneNumber(randomNumberString());
		rp.password(randomAlphaNumericString());
		rp.confirmPassword(randomAlphaNumericString());
		rp.agree();
		rp.continueButton();
		logger.info("Filled the Registration page");
		
		Assert.assertEquals(rp.passwordErrorMsg(),"Password confirmation does not match password!");
		logger.info("Test Case Passed and error message regarding the password is received");
		}
		catch(Exception e)
		{
			logger.info("Test Case Failed "+e.getMessage());
			Assert.fail("Test Case Failed "+e.getMessage());
		}
		finally
		{
			logger.info("Finished the TC003_UserRegistrationPasswordErrorMsgTest");
		}
	}

}
