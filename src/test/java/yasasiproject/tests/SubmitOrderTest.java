package yasasiproject.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import yasasiproject.TestComponents.BaseTest;
import yasasiproject.pageobject.CartPage;
import yasasiproject.pageobject.CheckoutPage;
import yasasiproject.pageobject.ConfirmationPage;
import yasasiproject.pageobject.OrderPage;
import yasasiproject.pageobject.ProductCatelogue;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test (dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
		
		
		
		ProductCatelogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products = productCatalogue.getProductList();
		productCatalogue.addProductsToCart(input.get("product"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
	
	
	//To verify ZARA COAT 3 is displaying in orders page
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		
		ProductCatelogue productCatalogue = landingPage.loginApplication("yasasi@gmail.com", "Madri1qaz2wsx@");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
		
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty
				("user.dir")+"//src//test//java//yasasiproject//data//PurchaseOrder.json");
		return new Object[][]  {{data.get(0)}, {data.get(1) } };
		
	}
	

}



