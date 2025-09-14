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

public class ChromePopupTest extends BaseTest {

	private WebDriverWait wait;

	@BeforeClass
	public void setupChrome() throws MalformedURLException {
		setup("com.android.chrome", "com.google.android.apps.chrome.Main");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@Test
	public void testPopupLogin() {
		driver.get("https://training-support.net/webelements");		
		wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

		WebElement popupsLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Popups")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", popupsLink);
		popupsLink.click();
	
		WebElement signInBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[normalize-space()='Sign In']")));
		signInBtn.click();

		WebElement user = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
		WebElement pass = driver.findElement(By.id("password"));
		user.clear();
		user.sendKeys("admin");
		pass.clear();
		pass.sendKeys("password");

		driver.findElement(By.xpath("//button[normalize-space()='Log in']")).click();

		String message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("action-confirmation")))
				.getText();
		Assert.assertEquals(message, "Welcome Back, admin");
	}

	@AfterClass
	public void tearDown() {
		quit();
	}
}