package Activities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestActivity1 {
	
	WebDriver driver ;
	WebDriverWait wait;
	
	@BeforeClass
	public void SetUp() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://training-support.net");
	}
    
	@Test(priority = 1)
	public void HomepageTest() {
		Assert.assertEquals(driver.getTitle(), "Training Support");
	}
	
	@Test(priority = 2)
	public void AboutpageTest() {
		driver.findElement(By.linkText("About Us")).click();
		Assert.assertEquals(driver.getTitle(), "About Training Support");
	}
	
	@AfterClass
	public void CleanUp() {
		driver.quit();
	}

}