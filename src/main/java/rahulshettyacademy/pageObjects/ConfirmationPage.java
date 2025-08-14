package rahulshettyacademy.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.reusableComponents.ReusableClass;

public class ConfirmationPage extends ReusableClass{
	
	WebDriver driver;
	
	@FindBy(css = "h1.hero-primary")
	WebElement confirmation;
	
	@FindBy(xpath = "//label[@class='ng-star-inserted']")
	WebElement orderID;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public String checkConfirmationMessage() {
		String confirmationMessage = confirmation.getText();
		return confirmationMessage;
	}
	
	public String getOrderID() {
		return orderID.getText();
	}

}
