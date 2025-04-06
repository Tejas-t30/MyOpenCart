package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
		
	}
	@FindBy(xpath="//span[@class='caret']") WebElement lnkMyAccount;
	@FindBy(xpath="//a[normalize-space()='Register']") WebElement lnkRegister;
	@FindBy(xpath="//a[normalize-space()='Login']") WebElement lnkLogin;
	public void myAccountClick()
	{
		lnkMyAccount.click();
	}
	public void registerClick()
	{
		lnkRegister.click();
	}
	public void loginClick()
	{
		lnkLogin.click();
	}
	

}
