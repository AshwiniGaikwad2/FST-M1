package Activities;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestActivity7 {
	WebDriver driver ;
	WebDriverWait wait;
	
	@BeforeClass
	public void SetUp() {
		driver = new FirefoxDriver();
		wait = new WebDriverWait(driver,Duration.ofSeconds(10));
		driver.get("https://training-support.net/webelements/login-form");
	}
    
	@Test
	public void PageTitleTest() {
		assertEquals(driver.getTitle(), "Selenium: Login Form");
	}
	
	@DataProvider(name="InvalidCredentials")
	public Object[][] creds(){
		return new Object[][] {
			{"admin","incorrectPassword"},
			{"incorrectAdmin","password"},
			{"admin","!@#&^%*()"},
			{"DELETE TABLE users;","password"}
		};
	}
		
	@Test(dataProvider = "InvalidCredentials")
	public void invalidLoginTest(String username,String password) {
	
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
		
		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys(username);
		passwordField.sendKeys(password);
		submitButton.click();
		
		String msg = driver.findElement(By.id("subheading")).getText();
		assertEquals(msg,"Invalid credentials");
		
	}
	
	@Test
	public void validLoginTest() {
		WebElement usernameField = driver.findElement(By.id("username"));
		WebElement passwordField = driver.findElement(By.id("password"));
		WebElement submitButton = driver.findElement(By.xpath("//button[text()='Submit']"));
		
		usernameField.clear();
		passwordField.clear();
		usernameField.sendKeys("admin");
		passwordField.sendKeys("password");
		submitButton.click();
		
		String msg = driver.findElement(By.cssSelector("h2.text-gray-800")).getText();
		assertEquals(msg,"Welcome Back, Admin!");
		
	}
	
	@AfterClass
	public void CleanUp() {
		driver.quit();
	}

}
