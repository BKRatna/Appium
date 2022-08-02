package Practise.AppiumFramework;
import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.Test;

import PageObjects.Dependencies;
import PageObjects.HomePage;
import PageObjects.Preferences;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class APIDemos_TC1 extends Base{

	@Test
	public void APIDemos() throws InterruptedException, IOException  {
		
		service = startServer();
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver = capabilities("APIDemos");
		//Xpath syntax= //tagName[@attribute='value']
		
		HomePage h = new HomePage(driver);
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();
		h.Preferences.click();
		Thread.sleep(3000);
		
		Preferences p = new Preferences(driver);
		//driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		p.dependencies.click();
		Thread.sleep(3000);
		
		Dependencies d = new Dependencies(driver);
		//driver.findElementById("android:id/checkbox").click();
		d.wifiCheckbox.click();
		//driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		d.wifiSettings.click();
		//driver.findElementByClassName("android.widget.EditText").sendKeys("Hello");
		d.wifiSettingsEditText.sendKeys("Hello");
		Thread.sleep(3000);
		//driver.findElementsByClassName("android.widget.Button").get(1).click();
		d.wifiSettingsOkBtn.get(1).click();
		service.stop();
		
	}

}
