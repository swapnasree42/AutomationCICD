package rahulshettyacademy.Tests;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.OrderHistoryPage;
import rahulshettyacademy.pageObjects.ProductsCateloguePage;

public class SubmitOrderTest extends BaseTest{
	
	public String productName = "IPHONE 13 PRO";
	
	@Test(dataProvider = "getData", groups = "Purchase")
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		
		ProductsCateloguePage productCateloguePage = landingPage.loginToApplication(input.get("email"), input.get("password"));
		 
		//List<WebElement> items = productCateloguePage.getProductList();
		productCateloguePage.addProductToCart(input.get("productName"));
		CartPage cartPage = productCateloguePage.goToCartPage();
		
		//List<WebElement> cartItems = cartPage.getCartItems();
		boolean isItemPresentInCart = cartPage.isItemPresentInCart(input.get("productName"));
		Assert.assertTrue(isItemPresentInCart);
		CheckoutPage checkOutPage= cartPage.goToCheckoutPage();
		
		checkOutPage.enterCheckOutPageInformation();
		ConfirmationPage confirmationPage = checkOutPage.gotoConfirmationPage();
		
		String confirmationMessage = confirmationPage.checkConfirmationMessage();
		Assert.assertEquals(confirmationMessage, "THANKYOU FOR THE ORDER.");
		String orderID = confirmationPage.getOrderID();
		System.out.println("Thank you for the Order. Your Order ID: "+orderID);
		
	}
	
	@Test(dependsOnMethods = "submitOrder")
	public void orderHistoryTest() {
		landingPage.loginToApplication("swapna@gamil.com", "Swapna@123$");
		OrderHistoryPage ordersHistoryPage = new OrderHistoryPage(driver);
		ordersHistoryPage.goToOrdersPage();
		boolean match = ordersHistoryPage.verifyOrderDisplay(productName);
		Assert.assertTrue(match);
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\Purchase.json");
		
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
	/*@DataProvider
	public Object[][] getData() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "swapna@gamil.com");
		map.put("password", "Swapna@123$");
		map.put("productName", "IPHONE 13 PRO");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "alavalapati@gmail.com");
		map1.put("password", "Rupa@123");
		map1.put("productName", "ZARA COAT 3");
		
		return new Object[][] {{map},{map1}};
	}*/
	
	/*@DataProvider
	public Object[][] getData() {
		return new Object[][]{
			{"swapna@gamil.com", "Swapna@123$","IPHONE 13 PRO"},
			{"alavalapati@gmail.com", "Rupa@123", "ZARA COAT 3"}
			};
	}*/
}
