package Practise.AppiumFramework;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import PageObjects.APIDemosFormPage;

import org.testng.AssertJUnit;
import static java.time.Duration.ofSeconds;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.appium.java_client.touch.TapOptions.tapOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.LongPressOptions.longPressOptions;

public class eCommerce_TC3 extends Base {

	@Test
	public void totalValidation() throws IOException, InterruptedException {
		
		service = startServer();
		AndroidDriver<AndroidElement> driver = capabilities("GeneralStoreApp");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		APIDemosFormPage a= new APIDemosFormPage(driver);
		
		a.getNameField().sendKeys("Ratna");
		//OR IN THIS WAY a.nameField.sendKeys("Ratna");
		driver.hideKeyboard();
		a.radioFemale.click();
		a.countryDropdown.click();

		Utilities u = new Utilities(driver);
		MobileElement element = u.scrollToText("Antarctica");
		element.click();

		a.letsShopBtn.click();
		a.addToCartBtn.get(0).click();
		a.addToCartBtn.get(0).click();
		a.Cart.click();
		Thread.sleep(4000);

		int count = a.ProductsPrice.size();
		System.out.println(count);
		double sum = 0;

		Thread.sleep(4000);

		for (int i = 0; i < count; i++)

		{

			String amount1 = a.ProductsPrice.get(i)
					.getText();
			double amount = getCorrectValue(amount1);
			sum = sum + amount;
			System.out.println("sum");

		}

		System.out.println(sum + "sum of products");
		String total1 = a.TotalValue.getText();
		double total = getCorrectValue(total1);
		System.out.println(total + "Total value of products");
		AssertJUnit.assertEquals(sum, total);

		// Mobile Gestures
		a.emailCheckbox.click();
		TouchAction t = new TouchAction(driver);
		WebElement terms = a.TermsandConditions;
		t.longPress(longPressOptions().withElement(element(terms)).withDuration(ofSeconds(2))).release().perform();
		Thread.sleep(3000);
		a.TermsCloseBtn.click();
		Thread.sleep(3000);
		a.ProceedBtn.click();
		Thread.sleep(7000);
		Set<String> contexts = driver.getContextHandles();
		for (String contextName : contexts) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");

		driver.findElementByName("q").sendKeys("Hello");
		driver.findElementByName("q").sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		driver.context("NATIVE_APP");
		service.stop();
	}

	@BeforeTest
	public void killAllNodes() throws IOException, InterruptedException {
		
		Runtime.getRuntime().exec("killall node");
		Thread.sleep(3000);
		System.out.println("Killed all running node processes");
	}
	
	
	
	public static double getCorrectValue(String value) {

		String price = value.substring(1);
		double Amt = Double.parseDouble(price);
		return Amt;
	}	

}
