package Practise.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Base {

	public static AppiumDriverLocalService service;

	public AppiumDriverLocalService startServer() {
		//
		boolean flag = checkIfServerIsRunnning(4723);
		if (!flag) {

			AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
			// Use any port, in case the default 4723 is already taken (maybe by another
			// Appium server)
			serviceBuilder.usingAnyFreePort();
			// Tell serviceBuilder where node is installed. Or set this path in an
			// environment variable named NODE_PATH
			serviceBuilder.usingDriverExecutable(new File("/usr/local/lib/nodejs/node-v16.15.0-darwin-x64/bin/node"));
			// Tell serviceBuilder where Appium is installed. Or set this path in an
			// environment variable named APPIUM_PATH
			serviceBuilder.withAppiumJS(new File("/usr/local/bin/appium"));
			// The XCUITest driver requires that a path to the Carthage binary is in the
			// PATH variable. I have this set for my shell, but the Java process does not
			// see it. It can be inserted here.
			HashMap<String, String> environment = new HashMap();
			environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
			serviceBuilder.withEnvironment(environment);

			service = AppiumDriverLocalService.buildService(serviceBuilder);
			service.start();
			// service=AppiumDriverLocalService.buildDefaultService();
			// service.start();

			/*
			 * String Appium_Node_Path =
			 * "/usr/local/lib/nodejs/node-v16.15.0-darwin-x64/bin/node"; String
			 * Appium_JS_Path = "/usr/local/lib/node_modules/appium/build/lib/main.js";
			 * service = AppiumDriverLocalService.buildService(new
			 * AppiumServiceBuilder().usingAnyFreePort() .usingDriverExecutable(new
			 * File(Appium_Node_Path)).withAppiumJS(new File(Appium_JS_Path)));
			 * service.start();
			 */

		}
		return service;

	}

	public static boolean checkIfServerIsRunnning(int port) {

		boolean isServerRunning = false;
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(port);

			serverSocket.close();
		} catch (IOException e) {
			// If control comes here, then it means that the port is in use
			isServerRunning = true;
		} finally {
			serverSocket = null;
		}
		return isServerRunning;
	}

	
	//Method to start emulator through code Start  
//	public static void startEmulator() throws IOException, InterruptedException {
//	  
//	  Runtime.getRuntime().exec(System.getProperty("user.dir") +
//	  "/src/main/java/resources/startEmulator.sh"); Thread.sleep(6000); }
//	 
//End
	public static AndroidDriver<AndroidElement> capabilities(String appName) throws IOException, InterruptedException {

		// TODO Auto-generated method stub
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "/src/main/java/Practise/AppiumFramework/global.properties");
		Properties prop = new Properties();
		prop.load(fis);

		File appDir = new File("src");
		File app = new File(appDir, (String) prop.get(appName));
		//To initiate the start emulator method
//		  String device = (String) prop.get("Device"); if (device.contains("Emulator"))
//		  { startEmulator(); }
		//end
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, (String) prop.get("Device"));
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 10);

		AndroidDriver<AndroidElement> driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), cap);

		return driver;

	}

}
