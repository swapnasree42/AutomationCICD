package rahulshettyacademy.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import rahulshettyacademy.reusableComponents.ReusableClass;

public class OrderHistoryPage extends ReusableClass{
	
	WebDriver driver;
	
	public OrderHistoryPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = "tr.ng-star-inserted")
	List<WebElement> ordersList;
	
	public boolean verifyOrderDisplay(String productName) {
		boolean match = ordersList.stream().anyMatch(order -> order.findElement(By.cssSelector("td:nth-child(3)")).getText().equals(productName));
		return match;
	}

}
