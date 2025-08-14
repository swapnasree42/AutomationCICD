package rahulshettyacademy.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.ProductsCateloguePage;
import rahulshettyacademy.TestComponents.Retry;

public class ErrorValidationsTest extends BaseTest{
	
	@Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {
		
		landingPage.loginToApplication("swapna@gamil.com", "WrongPassword");
		String errorMessage = landingPage.VerifyLoginErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}
	
	@Test
	public void cartProductErrorValidation() throws InterruptedException, IOException {
		
		String productName = "IPHONE 13 PRO";
		ProductsCateloguePage productCateloguePage = landingPage.loginToApplication("swapna@gamil.com", "Swapna@123$");
	
		//List<WebElement> items = productCateloguePage.getProductList();
		productCateloguePage.addProductToCart(productName);
		CartPage cartPage = productCateloguePage.goToCartPage();
		
		//List<WebElement> cartItems = cartPage.getCartItems();
		boolean isItemPresentInCart = cartPage.isItemPresentInCart("Wrong Product");
		Assert.assertFalse(isItemPresentInCart);
		
	}
}
