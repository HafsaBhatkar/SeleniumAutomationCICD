package companyName.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import companyName.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {


	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Elements
	@FindBy(css = "h1[class='hero-primary']")
	WebElement successMesgDisplayed;
	
	
	//Locator
	By orderPlacedMsg = By.cssSelector("div[aria-label='Order Placed Successfully']");
	
	public String getConfirmationMsg() {
		waitForElementToAppear(orderPlacedMsg);
		String succesMsg = successMesgDisplayed.getText();
		return succesMsg;
	}

}
