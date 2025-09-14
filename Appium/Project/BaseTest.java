package Activities;

import java.net.URL;
import java.time.Duration;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class BaseTest {
	protected AndroidDriver driver;

	@SuppressWarnings("deprecation")
	protected void setup(String appPackage, String appActivity) {
		UiAutomator2Options options = new UiAutomator2Options().setPlatformName("Android")
				.setDeviceName("emulator-5554").setAutomationName("UiAutomator2").setAppPackage(appPackage)
				.setAppActivity(appActivity);

		try {
			driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), options);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected void quit() {
		if (driver != null)
			driver.quit();
	}
}