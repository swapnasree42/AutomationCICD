package rahulshettyacademy.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.reusableComponents.ReusableClass;

public class CartPage extends ReusableClass{
	
	WebDriver driver;
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItems;
	
	@FindBy(xpath = "//button[contains(text(),'Checkout')]")
	WebElement checkOutButton;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public List<WebElement> getCartItems() {
		return cartItems;
	}
	
	public boolean isItemPresentInCart(String productName) {
		return cartItems.stream().anyMatch(cartItem -> cartItem.getText().equals(productName));
	}
	
	public CheckoutPage goToCheckoutPage() {
		checkOutButton.click();
		CheckoutPage checkOutPage = new CheckoutPage(driver);
		return checkOutPage;
	}

}
