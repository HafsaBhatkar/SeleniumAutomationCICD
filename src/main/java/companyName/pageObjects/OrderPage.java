package companyName.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import companyName.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{

	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	// Locators
	By productTitle = By.cssSelector("tr td:nth-child(3)");
	
	public List<WebElement> getAllProductsInCart(){
		List<WebElement> cartProducts = driver.findElements(productTitle);
		return cartProducts;
	}
	
	public boolean verifyProductInCart(String productName) {
		waitForAllElementsToAppear(productTitle);
		Boolean productFound = getAllProductsInCart().stream().anyMatch(p -> p.getText().equalsIgnoreCase(productName));
		return productFound;
	}

}
