package companyName.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import companyName.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents {

	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countriesDropdown;
	
	@FindBy(css=".ta-item")
	List<WebElement> countryList;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement placeOrderButton;
	
	//Locator
	By countriesDropdownBy = By.cssSelector("input[placeholder='Select Country']");
	
	
	public ConfirmationPage fillCheckoutDetails(String country) {
		waitForElementToAppear(countriesDropdownBy);
		countriesDropdown.sendKeys(country);
		findCountry(country);
		moveToElementAndClick(placeOrderButton);
		return new ConfirmationPage(driver);
	}
	
	public void findCountry(String country) {
		List<WebElement> countries = countryList;
		WebElement countryFound = countries.stream()
				.filter(c -> c.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		if(countryFound != null) {
			countryFound.click();
		}else {
			System.err.println("Country not found.");
		}
	}
}
