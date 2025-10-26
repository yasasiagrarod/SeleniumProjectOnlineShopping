package yasasiproject.tests;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import yasasiproject.TestComponents.BaseTest;
import yasasiproject.pageobject.CartPage;
import yasasiproject.pageobject.CheckoutPage;
import yasasiproject.pageobject.ConfirmationPage;
import yasasiproject.pageobject.ProductCatelogue;


public class ErrorValidationsTest extends BaseTest{

	@Test (groups= {"ErrorHandling"}, retryAnalyzer=yasasiproject.TestComponents.Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		landingPage.loginApplication("yasasi@gmail.com", "Madri1qaz2wsx");
		Assert.assertEquals( "Incorrect email or password.", landingPage.getErrorMessage());
		
		//Incorrect email or password.

	}
	
	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		
		String productName = "ZARA COAT 3";
		
		ProductCatelogue productCatalogue = landingPage.loginApplication("yasasi@gmail.com", "Madri1qaz2wsx@");
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductsToCart(productName);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	
	
	

	}

}
