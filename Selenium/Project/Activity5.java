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

public class Activity5 {

	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
       	driver = new FirefoxDriver();
       	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
       	driver.get("https://alchemy.hguy.co/lms");
       }

    @Test
    public void testNavigateToMyAccountPage() {     
               
        WebElement myAccountLink = driver.findElement(By.linkText("My Account"));
        myAccountLink.click();      
        String actualTitle = driver.getTitle();
        String expectedTitle = "My Account – Alchemy LMS";
        Assert.assertEquals(actualTitle, expectedTitle, "Page title doesn't match! Navigation might have failed.");
    }

    @AfterClass
    public void tearDown() {
  	   
        driver.quit();     

    }
}
