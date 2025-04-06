package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegisterPage extends BasePage{

	public RegisterPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txtFirstName;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txtLastName;
	@FindBy(xpath="//input[@id='input-email']") WebElement txtEmail;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txtPhoneNo;
	@FindBy(xpath="//input[@id='input-password']")WebElement txtPassword;
	@FindBy(xpath="//input[@id='input-confirm']")WebElement txtConfirmPassword;
	@FindBy(xpath="//input[@name='agree']") WebElement chkAgree;
	@FindBy(xpath="//input[@value='Continue']") WebElement btnContinue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement confirmMsg;
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']") WebElement warningMsg;
	@FindBy(xpath="//div[@class='text-danger']") WebElement pswdErrorMsg;
	public void firstName(String firstName)
	{
		txtFirstName.sendKeys(firstName);
	}
	public void LastName(String lastName)
	{
		txtLastName.sendKeys(lastName);
	}
	public void email(String email)
	{
		txtEmail.sendKeys(email);
	}
	public void phoneNumber(String phNumber)
	{
		txtPhoneNo.sendKeys(phNumber);
	}
	public void password(String pswd)
	{
		txtPassword.sendKeys(pswd);
	}
	public void confirmPassword(String confirmPswd)
	{
		txtConfirmPassword.sendKeys(confirmPswd);
	}
	public void agree()
	{
		chkAgree.click();
	}
	public void continueButton()
	{
		btnContinue.click();
	}
	
	public String confirmationMsg()
	{
		try {
			return confirmMsg.getText();
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	
	public String warningMsg()
	{
		try {
			return warningMsg.getText();
		}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	public String passwordErrorMsg()
	{
		try {
			return pswdErrorMsg.getText();
			}
		catch(Exception e)
		{
			return (e.getMessage());
		}
	}
	

}
