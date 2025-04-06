package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MyAccountPage extends BasePage{
	public MyAccountPage(WebDriver driver)
	{
		super(driver);
	}
	@FindBy(xpath="//h2[text()='My Account']") WebElement txtMyAccount;
	@FindBy(xpath="(//a[@class='list-group-item'][normalize-space()='Logout'])[1]") WebElement lnkLogout;
	
	public boolean ismyAccountTxtDisplayed()
	{ try {
			return txtMyAccount.isDisplayed();
	      }
		  catch(Exception e)
		  {
			  return false;
		  }
	}
	public void logoutClick()
	{
		lnkLogout.click();
	}

}
