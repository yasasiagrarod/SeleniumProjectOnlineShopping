package yasasiproject.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yasasiproject.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	@FindBy(css = ".action__submit")
	 private WebElement submit;
	
	@FindBy(css = "[placeholder='Select Country']")
	private WebElement country;
	
	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	private By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(By.cssSelector(".ta-results"));
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		//submit.click();
		//return new ConfirmationPage(driver);
		
		// 🟢 Wait until submit button is clickable
				waitForElementToBeClickable(submit);

				// 🟢 Scroll into view (to ensure button is visible)
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);

				// 🟢 Add a small pause if animation still overlaps
				try {
					Thread.sleep(500); // just half a second buffer
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 🟢 Try clicking safely
				try {
					submit.click();
				} catch (ElementClickInterceptedException e) {
					// 🟢 Fallback to JS click if intercepted
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", submit);
				}

				return new ConfirmationPage(driver);
			}
		
		
	}
	
	

