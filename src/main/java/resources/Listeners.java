package resources;

import java.io.IOException;

import org.testng.ITestListener;
import org.testng.ITestResult;

import Practise.AppiumFramework.Utilities;


public class Listeners implements ITestListener {
	

	public void onTestFailure(ITestResult result) {  
		// TODO Auto-generated method stub  
		String s= result.getName();
		System.out.println("Failure of test cases and its details are : "+s);  
		try {
			
			Utilities.getScreenshot(s);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

}