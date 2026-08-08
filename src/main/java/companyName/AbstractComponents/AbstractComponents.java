package companyName.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import companyName.pageObjects.CartPage;
import companyName.pageObjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	Actions action;
	
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		this.action = new Actions(driver);
	}

	public void waitForElementToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForAllElementsToAppear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}
	
	public void waitForElementToDisappear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void moveToElementAndClick(WebElement element) {
		action.moveToElement(element).click().build().perform();;
	}
	
	@FindBy(css="button[routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	public CartPage goToCartPage() {
		cartButton.click();
		return new CartPage(driver);
	}
	
	@FindBy(css="button[routerlink='/dashboard/myorders']")
	WebElement ordersButton;
	
	public OrderPage goToOrdersPage() {
		ordersButton.click();
		return new OrderPage(driver);
	}
}
