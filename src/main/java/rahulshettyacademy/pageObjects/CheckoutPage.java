package rahulshettyacademy.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.reusableComponents.ReusableClass;

public class CheckoutPage extends ReusableClass{
	
	WebDriver driver;
	
	@FindBy(css = ".form__cc div:nth-child(2) input")
	WebElement cvvTextBox;
	
	@FindBy(css = ".row:nth-child(3) input")
	WebElement name;
	
	@FindBy(css = "[name='coupon']")
	WebElement coupon;
	
	@FindBy(className = "btn-primary")
	WebElement applyButton;
	
	@FindBy(xpath = "//p[contains(text(),'* Coupon Applied')]")
	WebElement couponText;
	
	@FindBy(css = "input[placeholder='Select Country']")
	WebElement countryBox;
	
	@FindBy(css = "span.ng-star-inserted")
	List<WebElement> countryResults;
	
	@FindBy(css = "a.action__submit")
	WebElement placeOrderButton;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterCheckOutPageInformation() throws InterruptedException {
		cvvTextBox.sendKeys("ABC");
		name.sendKeys("Swapna Reddy");
		coupon.sendKeys("rahulshettyacademy");
		applyButton.click();
		visibilityOf(couponText);
		countryBox.sendKeys("India");
		WebElement country = countryResults.stream().filter(countryResult -> countryResult.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		country.click();
	}
	
	public ConfirmationPage gotoConfirmationPage() {
		placeOrderButton.click();
		ConfirmationPage confirmationPage = new ConfirmationPage(driver);
		return confirmationPage;
	}

}
