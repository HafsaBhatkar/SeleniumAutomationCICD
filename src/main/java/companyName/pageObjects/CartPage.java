package companyName.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import companyName.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents{

	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	By productTitle = By.cssSelector("div[class='cartSection'] h3");
	
	public List<WebElement> getAllProductsInCart(){
		List<WebElement> cartProducts = driver.findElements(productTitle);
		return cartProducts;
	}
	
	public boolean verifyProductInCart(String productName) {
		//goToCartPage();
		waitForAllElementsToAppear(productTitle);
		Boolean productFound = getAllProductsInCart().stream().anyMatch(p -> p.getText().equals(productName));
		return productFound;
	}
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutBtn;
	
	By checkoutBtnBy = By.xpath("//button[text()='Checkout']");
	
	public CheckoutPage goToCheckout() {
		waitForElementToAppear(checkoutBtnBy);
		checkOutBtn.click();
		return new CheckoutPage(driver);
	}

}
