package yasasiproject.pageobject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import yasasiproject.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver; 
	
	public LandingPage(WebDriver driver)
	
	{
		
		super(driver);
		//initialization 
		this.driver= driver; 
		PageFactory.initElements(driver, this);
			
	}

	
	@FindBy (id="userEmail")
	WebElement userEmail;
	
	@FindBy (id="userPassword")
	WebElement passwordEle;
	
	@FindBy (id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	public ProductCatelogue loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();	
		ProductCatelogue productCatalogue = new ProductCatelogue (driver);
		return productCatalogue;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}

	

}
