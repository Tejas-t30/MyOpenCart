package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.RegisterPage;
import testBase.BaseClass;

public class TC002_UserRegistrationUsingExistingCredentialsTest extends BaseClass{
	@Test(groups= {"Master", "Registration"})
	public void registrationUsingValidCredentials()
	{
		logger.info("***Starting TC002_UserRegistrationUsingExistingCredentialsTest***");
		try {
		HomePage hp=new HomePage(driver);
		hp.myAccountClick();
		hp.registerClick();
		logger.info("Clicked on Registration Link");
		
		RegisterPage rp=new RegisterPage(driver);
		rp.firstName("Johnathan");
		rp.LastName("John");
		rp.email("Johnathan@gmail.com");
		rp.phoneNumber("2156451564");
		rp.password("John@123");
		rp.confirmPassword("John@123");
		rp.agree();
		rp.continueButton();
		logger.info("Filled the Account Registration form");
		
		Assert.assertEquals(rp.warningMsg(), "Warning: E-Mail Address is already registered!");
		logger.info("Test Case Passed and  warning regarding the email is received");
		}
		catch(Exception e)
		{
			logger.info("Test Failed "+e.getMessage());
			Assert.fail("Test Failed "+e.getMessage());
		}
		finally {
			logger.info("Finished the TC002_UserRegistrationUsingExistingCredentialsTest");
		}
		
	}

}
