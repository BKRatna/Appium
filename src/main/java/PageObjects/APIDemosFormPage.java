package PageObjects;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class APIDemosFormPage {
	
	public APIDemosFormPage(AppiumDriver driver) 
	{
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);	
		
	}
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	//or
	public WebElement getNameField()
	{
		System.out.println("To maintain the logs ie., Trying to find the Name field"); //This approach is used when the logs are needed for logging purpose
		return nameField;
	}
	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	public WebElement radioFemale;
	
	@AndroidFindBy(id="android:id/text1")
	public WebElement countryDropdown;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	public WebElement letsShopBtn;

	@AndroidFindBy(xpath="//*[@text='ADD TO CART']")
	public List<WebElement> addToCartBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	public WebElement Cart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	public List<WebElement> ProductsPrice;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	public WebElement TotalValue;
	
	@AndroidFindBy(className="android.widget.CheckBox")
	public WebElement emailCheckbox;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/termsButton")
	public WebElement TermsandConditions;
	
	@AndroidFindBy(id="android:id/button1")
	public WebElement TermsCloseBtn;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	public WebElement ProceedBtn;
	
}

