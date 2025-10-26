package yasasiproject.pageobject;

import java.util.List;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yasasiproject.AbstractComponents.AbstractComponent;


public class CartPage extends AbstractComponent {
	WebDriver driver;

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}

	public CheckoutPage goToCheckout() {
		//checkoutEle.click();
		//return new CheckoutPage(driver);
		
		waitForElementToBeClickable(checkoutEle);
		
		// 🔵 Scroll into view in case it's hidden
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutEle);
		
		// 🔵 Try clicking safely with JavaScript if normal click fails
		try {
			checkoutEle.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkoutEle);
		}

		return new CheckoutPage(driver);
		

	}

}
