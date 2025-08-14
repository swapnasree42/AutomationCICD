package rahulshettyacademy.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.reusableComponents.ReusableClass;

public class ProductsCateloguePage extends ReusableClass{
	
	WebDriver driver;
	
	@FindBy(className = "card-body")
	List<WebElement> items;
	
	@FindBy(css = "[aria-label='Product Added To Cart']")
	WebElement productAddedToCartWait;
	
	@FindBy(id = "toast-container")
	WebElement loadingWait;
	
	public ProductsCateloguePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	public List<WebElement> getProductList() {
		visibilityOfAllElementsLocatedBy(By.className("card-body"));
		return items;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream().filter(item -> 
		item.findElement(By.tagName("h5")).getText().equals(productName)).findFirst().orElse(null);
		return product;
	}
	
	public void addProductToCart(String productName) {
		getProductByName(productName).findElement(By.cssSelector("button.btn.w-10")).click();
		visibilityOf(productAddedToCartWait);
		invisibilityOf(loadingWait);
	}
}
