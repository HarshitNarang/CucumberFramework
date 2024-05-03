package PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login {

	WebDriver driver;

	public Login(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="Email")
	WebElement email;
	
	@FindBy(id="Password")
	WebElement password;
	
	@FindBy(xpath="//button[text()=\"Log in\"]")
	WebElement loginButton;
	
	@FindBy(xpath="//a[text()=\"Logout\"]")
	WebElement logout;
	
	@FindBy(xpath="//a[@href=\"#\"]/p[contains(text(),'Customers')]")
	WebElement customerMenu;
	
	@FindBy(xpath="//a[@href=\"/Admin/Customer/List\"]/p[contains(text(),'Customers')]")
	WebElement customerMenuItem;
	
	public void setEmail(String emailid) {
		email.clear();
		email.sendKeys(emailid);
	}
	
	public void setPassword(String pass) {
		password.clear();
		password.sendKeys(pass);
	}
	
	public void clickLogin() {
		loginButton.click();
	}
	public void clickLogout() {
		logout.click();
	}
	
	public void closeBrowser() {
		
	}

	public void clickCustomerMenu() {
		customerMenu.click();
		
	}
	public void clickCustomerMenuItem() {
		customerMenuItem.click();
	}
	
}

