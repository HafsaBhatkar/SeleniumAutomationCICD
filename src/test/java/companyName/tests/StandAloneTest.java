package companyName.tests;

import java.time.Duration;
import java.util.List;
import java.util.stream.Stream;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		System.out.println("START");
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();

		System.setProperty("webdriver.chrome.driver",
				"D:/Hafsa/Software Testing/Selenium/chromedriver-win64/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions mouse = new Actions(driver);
		
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// Login Page
		driver.findElement(By.id("userEmail")).sendKeys("susan1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Susan@1234");
		driver.findElement(By.id("login")).click();
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[aria-label='Login Successfully']"))));
		
		// Add To Cart
		String productName = "ZARA COAT 3"; 
		List<WebElement> productCard = driver.findElements(By.cssSelector(".mb-3"));
		WebElement product = productCard.stream().filter(p -> p.findElement(By.cssSelector(".mb-3 b")).getText().equals(productName)).findFirst().orElse(null);
		if(product != null) {
			System.out.println("Product with name "+ product.findElement(By.cssSelector("b")).getText() +" added to Cart");
			product.findElement(By.cssSelector("button:last-child")).click();
		}else {
			System.err.println("Product "+productName+" not Found !");
		}
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[aria-label='Product Added To Cart']"))));
		
		// Go to Cart
		mouse.moveToElement(driver.findElement(By.cssSelector("button[routerlink='/dashboard/cart']"))).click().build().perform();
		
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='cartSection'] h3")));
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='cartSection'] h3"));
		Boolean productFound = cartProducts.stream().anyMatch(p -> p.getText().equals(productName));
		Assert.assertTrue(productFound);
		
		//Checkout 
		Thread.sleep(3000);
		mouse.moveToElement(driver.findElement(By.xpath("//button[text()='Checkout']"))).click().build().perform();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[placeholder='Select Country']")));
		
		String country = "India";
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("ind");
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-item"));
		WebElement countryFound = countries.stream().filter(c -> c.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
		if(countryFound != null) {
			countryFound.click();
			mouse.moveToElement(driver.findElement(By.xpath("//a[text()='Place Order ']"))).click().build().perform();
		}else {
			System.err.println("Country not found.");
		}
		
		// Order Placed Successfully
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[aria-label='Order Placed Successfully']"))));
		String succesMsg = driver.findElement(By.cssSelector("h1[class='hero-primary']")).getText();
		succesMsg.equalsIgnoreCase(" Thankyou for the order. ");
		//Assert.assertEquals(succesMsg, " Thankyou for the order. ");
		System.out.println("END");
	}
}
