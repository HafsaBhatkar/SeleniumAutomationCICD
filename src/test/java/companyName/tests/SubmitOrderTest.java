package companyName.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import companyName.TestComponents.BaseTest;
import companyName.pageObjects.CartPage;
import companyName.pageObjects.CheckoutPage;
import companyName.pageObjects.ConfirmationPage;
import companyName.pageObjects.OrderPage;
import companyName.pageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest {

	// Commenting product name, as it is coming from json file now
	//String productNm = "ZARA COAT 3";

	@Test(dataProvider = "getData")
	public void SubmitOrder(HashMap<String, String> input) throws InterruptedException, IOException {

		// launchApplication();

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		productCatalog.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalog.goToCartPage();

		// Go to Cart
		Assert.assertTrue(cartPage.verifyProductInCart(input.get("productName")));

		// Checkout
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		ConfirmationPage confirmationPage = checkoutPage.fillCheckoutDetails(input.get("country"));

		// Order Placed Successfully
		String confirmMsg = " Thankyou for the order. ";
		confirmationPage.getConfirmationMsg().equalsIgnoreCase(confirmMsg);

	}

	@Test(dependsOnMethods = { "SubmitOrder" }, dataProvider = "getData")
	public void orderHistoryValidation(HashMap<String, String> input) {
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyProductInCart(input.get("productName")));
	}

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+ "\\src\\test\\java\\compantName\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0)}, {data.get(1) } };
	}

//		HashMap<String, String>  map1 = new HashMap<String, String>();
//		map1.put("email", "susan1@gmail.com");
//		map1.put("password", "Susan@1234");
//		map1.put("productName", "IPHONE 13 PRO");
//		
//		HashMap<String, String>  map2 = new HashMap<String, String>();
//		map2.put("email", "hannah22@gmail.com");
//		map2.put("password", "Hannah@12345");
//		map2.put("productName", "ZARA COAT 3");

//		return new Object[][] {
//							{map2},
//							{map1}};
//		}

//		return new Object[][] {
//						{"susan1@gmail.com", "Susan@1234", "ZARA COAT 3"},
//						{"hannah22@gmail.com","Hannah@12345","ADIDAS ORIGINAL"}};
//	}

}
