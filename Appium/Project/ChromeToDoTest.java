package Activities;

import java.net.MalformedURLException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ChromeToDoTest extends BaseTest {

	private WebDriverWait wait;

	@BeforeClass
	public void launchChrome() throws MalformedURLException {
		setup("com.android.chrome", "com.google.android.apps.chrome.Main");
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}

	@Test
	public void testToDoWebPage() {
		driver.get("https://training-support.net/webelements");

		wait.until(d -> ((JavascriptExecutor) d).executeScript("return document.readyState").equals("complete"));

		WebElement toDoLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("To-Do List")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", toDoLink);
		toDoLink.click();

		WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("taskInput")));

		List<WebElement> initialItems = driver.findElements(By.cssSelector("ul#tasks li"));
		int initialCount = initialItems.size();

		input.sendKeys("Add tasks to list");
		input.sendKeys("Get number of tasks");
		input.sendKeys("Clear the list");

		wait.until(d -> d.findElements(By.cssSelector("ul#tasks li")).size() >= initialCount + 3);

		int taskCount = driver.findElements(By.cssSelector("ul#tasks li")).size();
		Assert.assertEquals(taskCount, initialCount + 3, "Total tasks should increase by 3.");
	}

	@AfterClass
	public void tearDown() {
		quit();
	}
}