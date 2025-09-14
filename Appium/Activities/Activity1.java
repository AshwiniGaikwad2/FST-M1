package Activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class Activity1 {
	
	AppiumDriver driver ;
	WebDriverWait wait ;
	
	@BeforeClass
	public void setup() throws MalformedURLException, URISyntaxException {
		//Set capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("android");
		options.setAutomationName("UiAutomator2");
		options.noReset();
		options.setApp("C:/Users/003TCT744/Documents/WFS/Automation/FST/Calculator.apk");
		
		//Set Appium server URL
		URL serverUrl = new URI("http://localhost:4723").toURL();
		driver = new AndroidDriver(serverUrl,options);
	}
	@Test
	public void multiplyTest() {
    //	find and tap digit 2*5= one by one
		driver.findElement(AppiumBy.id("digit_2")).click();
		driver.findElement(AppiumBy.accessibilityId("multiply")).click();
		driver.findElement(AppiumBy.id("digit_5")).click();
		driver.findElement(AppiumBy.accessibilityId("equals")).click();
	//	find and assert the result
		String result = driver.findElement(AppiumBy.id("result_final")).getText();
		Assert.assertEquals(result,"10");
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}


}
