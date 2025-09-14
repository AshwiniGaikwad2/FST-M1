package Activities;

import java.net.MalformedURLException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChromeLoginTest extends BaseTest {

	private WebDriverWait wait;

	@BeforeClass
	public void setupChrome() throws MalformedURLException {
		setup("com.android.chrome", "com.google.android.apps.chrome.Main");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@Test
	public void testLoginWithValidCredentials() {
		driver.get("https://training-support.net/webelements");		
		wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

		WebElement loginLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Login Form")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", loginLink);
		loginLink.click();
		WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		WebElement pass = driver.findElement(By.id("password"));

		user.clear();
		user.sendKeys("admin");
		pass.clear();
		pass.sendKeys("password");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("action-confirmation")))
				.getText();
		Assert.assertEquals(message, "Welcome Back, admin");
	}

	@Test
	public void testLoginWithInvalidCredentials() {
		
		driver.navigate().refresh();

		WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		WebElement pass = driver.findElement(By.id("password"));

		user.clear();
		user.sendKeys("admin");
		pass.clear();
		pass.sendKeys("wrongpass");
		driver.findElement(By.cssSelector("button[type='submit']")).click();

		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("action-confirmation")))
				.getText();
		Assert.assertEquals(message, "Invalid Credentials");
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}
}