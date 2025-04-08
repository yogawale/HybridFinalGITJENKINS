package PageLayer;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SauceDemo {

	WebDriver ldriver;
	
	public SauceDemo(WebDriver rdriver){
		ldriver = rdriver;
		PageFactory.initElements(rdriver, this);
	}
	
	@FindBy(name = "user-name")
	private WebElement username;

	@FindBy(name = "password")
	private WebElement password;

	@FindBy(name = "login-button")
	private WebElement loginButton;
	
	
	public void enterUsername(String Uname) {
		username.clear();
		username.sendKeys(Uname);
	}
	
	public void enterPassword(String pwds) {
		password.clear();
		password.sendKeys(pwds);
	}
	
	public void clickBtn() {
		loginButton.click();
	}
	
	
}
