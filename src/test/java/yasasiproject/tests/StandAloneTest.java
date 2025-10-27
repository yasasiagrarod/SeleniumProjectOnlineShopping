package yasasiproject.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import yasasiproject.pageobject.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//new comments are added
		
		String productName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup(); //Chrome driver automatically download
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage landingpage = new LandingPage(driver);
		driver.manage().window().maximize(); 
		
		driver.findElement(By.id("userEmail")).sendKeys("yasasi@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Madri1qaz2wsx@");
		driver.findElement(By.id("login")).click();
		
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
		.orElse(null);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		
		WebElement button = driver.findElement(By.cssSelector(".totalRow button"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false);", button); // scroll with element at bottom
		js.executeScript("arguments[0].click();", button);
		
		
		//Actions a = new Actions(driver);
		//a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		WebElement countryInput = driver.findElement(By.cssSelector("[placeholder='Select Country']"));
		countryInput.sendKeys("India");

		// Wait for the autocomplete items to appear and click the second one
		List<WebElement> items = wait.until(
		    ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(".ta-item"))
		);
		items.get(1).click(); // click second item
		
		
		WebElement submitButton = driver.findElement(By.cssSelector(".action__submit"));

		// Scroll into view and click using the existing js
		js.executeScript("arguments[0].scrollIntoView(true);", submitButton);
		js.executeScript("arguments[0].click();", submitButton);
		
		
	
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	

	}

}
