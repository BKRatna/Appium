package Practise.AppiumFramework;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class Utilities {
	
	
	static AndroidDriver driver;
	public Utilities(AndroidDriver driver)
	{
		this.driver=driver;
	}
	
	//Android scrollToText
	public MobileElement scrollToText(String Text)
	{
		MobileElement element = (MobileElement) driver
		.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true))"
				+ ".scrollIntoView(new UiSelector().text(\""+Text+"\"))"));
		return element;
	}
	//Screenshot
	public static void getScreenshot(String s) throws IOException
	{
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"src/test/java/ListenersOutput"+s+".png"));
	}

}
