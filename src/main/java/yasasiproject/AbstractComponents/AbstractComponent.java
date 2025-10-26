package yasasiproject.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import yasasiproject.pageobject.CartPage;
import yasasiproject.pageobject.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	
	

	public void waitForElementToAppear(By findBy) {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(findBy));

	}
	
	
	public CartPage goToCartPage() {
		
		
		cartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
		
		
	}
	
	public OrderPage goToOrdersPage() {
		
		
		orderHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
		
	}

	public void waitForElementDisappear(WebElement ele) throws InterruptedException {
		
		Thread.sleep(1000);
		
		//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
		//wait.until(ExpectedConditions.invisibilityOf(ele));
		
		
	}
	
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}
	

}
