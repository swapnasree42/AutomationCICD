package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.reusableComponents.ReusableClass;

public class LandingPage extends ReusableClass{
	
	WebDriver driver;
	
	@FindBy(css = "#userEmail")
	WebElement email;
	
	@FindBy(xpath = "//input[@formcontrolname='userPassword']")
	WebElement password;
	
	@FindBy(css = "[value='Login']")
	WebElement loginButton;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement loginErrorMessage;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	public void goToApplication() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
	public ProductsCateloguePage loginToApplication(String emailInput, String passwordInput) {
		email.sendKeys(emailInput);
		password.sendKeys(passwordInput);
		loginButton.click();
		ProductsCateloguePage productsCateloguePage = new ProductsCateloguePage(driver);
		return productsCateloguePage;
	}
	
	public String VerifyLoginErrorMessage() {
		visibilityOf(loginErrorMessage);
		return loginErrorMessage.getText();
	}

}
