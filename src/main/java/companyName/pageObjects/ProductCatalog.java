package companyName.pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import companyName.AbstractComponents.AbstractComponents;

public class ProductCatalog extends AbstractComponents {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	By productBy = By.cssSelector(".mb-3");
	By addTCartBy = By.cssSelector("button:last-child");
	By animationSpinnerBy = By.cssSelector(".ng-animating");
	By addToCartToastBy = By.cssSelector("div[aria-label='Product Added To Cart']");

	public List<WebElement> getProductList() {
		waitForElementToAppear((productBy));
		List<WebElement> productList = driver.findElements(productBy);
		return productList;
	}

	public WebElement getProductByName(String productName) {
		WebElement product = getProductList().stream()
				.filter(p -> p.findElement(By.cssSelector(".mb-3 b")).getText().equals(productName)).findFirst()
				.orElse(null);

		if (product != null) {
			System.out.println(
					"Product with name " + product.findElement(By.cssSelector("b")).getText() + " added to Cart");
			return product;
		}
		System.err.println("Product " + productName + " not Found !");

		return null;

	}

	public CartPage addProductToCart(String product) throws InterruptedException {
		WebElement prod = getProductByName(product);
		prod.findElement(addTCartBy).click();
		waitForElementToAppear(addToCartToastBy);
		return new CartPage(driver);
	}

}
