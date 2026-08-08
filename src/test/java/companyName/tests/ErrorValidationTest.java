package companyName.tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import companyName.TestComponents.Retry;

import companyName.TestComponents.BaseTest;
import companyName.pageObjects.CartPage;
import companyName.pageObjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest {

	
	@Test(groups = {"Error Handling"}, retryAnalyzer = Retry.class)
	//@Test(groups = {"Error Handling"})
	public void loginErrorValidation() throws InterruptedException, IOException {
		//launchApplication();
		landingPage.loginApplication("susan11@gmail.com", "Susan@11234");
		Assert.assertEquals(landingPage.getErrorMsg(), "Incorrect email or password.");
	}

	@Test
	public void productCartErrorValidation() throws IOException, InterruptedException {
		String productName = "ADIDAS ORIGINAL";
		String wrongProductName = "ADIDAS ORIGINAL";
		ProductCatalog productCatalog = landingPage.loginApplication("susan1@gmail.com", "Susan@1234");
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Assert.assertTrue(cartPage.verifyProductInCart(wrongProductName));
	}
	
	//Adding comments to trigger the webhook
	
}
