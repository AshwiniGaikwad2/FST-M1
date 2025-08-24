package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity6 {

	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
       	driver = new FirefoxDriver();
       	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
       	driver.get("https://alchemy.hguy.co/lms");
       }

   @Test
   public void testLogin() throws InterruptedException {      
	   WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
       myAccountLink.click();     
       String actualTitle = driver.getTitle();
       String expectedTitle = "My Account – Alchemy LMS";
       
       if (actualTitle == expectedTitle) {
    	   
    	   System.out.println("Correct Page");
       }
       else {
    	   
    	   System.out.println("Wrong Page");
       }
       
       driver.findElement(By.linkText("#login")).click();
       
       WebElement usernameField = driver.findElement(By.id("username")); 
       usernameField.sendKeys("root");
      
       WebElement passwordField = driver.findElement(By.id("password")); 
       passwordField.sendKeys("pa$$w0rd");
      
       WebElement loginButton = driver.findElement(By.id("loginButton")); 
       loginButton.click();
       
       WebElement dashboard = driver.findElement(By.id("dashboard")); 
       Assert.assertTrue(dashboard.isDisplayed(), "Login failed: Dashboard not found.");
   }

   @AfterClass
   public void tearDown() {
  	   
       driver.quit();     

   }
}
