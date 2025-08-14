package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class StandaloneTest {
	
	@Test
	public void standAlone() throws InterruptedException {
		
		String productName = "IPHONE 13 PRO";
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.cssSelector("#userEmail")).sendKeys("swapna@gamil.com");
		driver.findElement(By.xpath("//input[@formcontrolname='userPassword']")).sendKeys("Swapna@123$");
		driver.findElement(By.cssSelector("[value='Login']")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("card-body")));
		
		List<WebElement> items = driver.findElements(By.className("card-body"));
		//System.out.println(items.size());
		
		WebElement product = items.stream().filter(item ->
		item.findElement(By.tagName("h5")).getText().equals(productName)).findFirst().orElse(null);
		product.findElement(By.cssSelector("button.btn.w-10")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("[aria-label='Product Added To Cart']"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.id("toast-container"))));
		
		driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
		
		Thread.sleep(2000);
		List<WebElement> cartItems = driver.findElements(By.cssSelector(".cartSection h3"));
		
		boolean isItemPresentInCart = cartItems.stream().anyMatch(cartItem -> cartItem.getText().equals(productName));
		Assert.assertTrue(isItemPresentInCart);
		
		driver.findElement(By.xpath("//button[contains(text(),'Checkout')]")).click();
		
		WebElement cvvText = driver.findElement(By.xpath("//div[contains(text(),'CVV Code ')]"));
		driver.findElement(with(By.tagName("input")).below(cvvText)).sendKeys("ABC");
		
		driver.findElement(By.cssSelector(".row:nth-child(3) input")).sendKeys("Swapna Reddy");
		
		driver.findElement(By.cssSelector("[name='coupon']")).sendKeys("rahulshettyacademy");
		driver.findElement(By.className("btn-primary")).click();
		
		//Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'* Coupon Applied')]")));
		//System.out.println(driver.findElement(By.xpath("//p[contains(text(),'* Coupon Applied')]")).getText());
		
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
		List<WebElement> countryResults = driver.findElements(By.cssSelector("span.ng-star-inserted"));
		
		WebElement country = countryResults.stream().filter(countryResult -> countryResult.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		country.click();
		
		driver.findElement(By.cssSelector("a.action__submit")).click();
		
		String confirmationMessage = driver.findElement(By.cssSelector("h1.hero-primary")).getText();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		
		String orderID = driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText();
		System.out.println("Thank you for the Order. Your Order ID: "+orderID);
		
		driver.findElement(By.cssSelector("[routerlink='/dashboard/myorders']")).click();
		List<WebElement> ordersList = driver.findElements(By.cssSelector("tr.ng-star-inserted"));
		boolean match = ordersList.stream().anyMatch(order -> order.findElement(By.cssSelector("td:nth-child(3)")).getText().equals(productName));
		Assert.assertTrue(match);
		
		driver.quit();
	}

}
