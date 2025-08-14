package rahulshettyacademy.stepDefinations;

import java.io.IOException;
import org.testng.Assert;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.ProductsCateloguePage;

public class StepDefinationImpl extends BaseTest{
	
	LandingPage landingPage;
	ProductsCateloguePage productCateloguePage;
	CartPage cartPage;
	CheckoutPage checkOutPage;
	ConfirmationPage confirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^I logged in with userName (.+) and password (.+)$")
	public void i_logged_in_with_userName_and_password(String userName, String password) {
		productCateloguePage = landingPage.loginToApplication(userName, password);
		
	}
	
	@When("^I add product (.+) to the Cart$")
	public void when_i_add_product_to_the_cart(String product) {
		productCateloguePage.addProductToCart(product);
	}
	
	@And("^Checkout product (.+) and submit the order$")
	public void checkout_product_and_submit_the_order(String product) throws InterruptedException {
		cartPage = productCateloguePage.goToCartPage();
		
		//List<WebElement> cartItems = cartPage.getCartItems();
		boolean isItemPresentInCart = cartPage.isItemPresentInCart(product);
		Assert.assertTrue(isItemPresentInCart);
		checkOutPage = cartPage.goToCheckoutPage();
		
		checkOutPage.enterCheckOutPageInformation();
		confirmationPage = checkOutPage.gotoConfirmationPage();
	}
	
	@Then("{string} message is displayed on the ConfirmationPage")
	public void message_is_displayed_on_the_confirmationPage(String string) {
		String confirmationMessage = confirmationPage.checkConfirmationMessage();
		Assert.assertEquals(confirmationMessage, string);
		String orderID = confirmationPage.getOrderID();
		System.out.println(string+" Your Order ID: "+orderID);
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		String errorMessage = landingPage.VerifyLoginErrorMessage();
		Assert.assertEquals(errorMessage, string);
		driver.close();
	}

}
