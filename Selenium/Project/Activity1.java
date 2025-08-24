package project;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity1 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
    	driver = new FirefoxDriver();
    	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	driver.get("https://alchemy.hguy.co/lms");
    }

    @Test
    public void verifyTitle() {  
        String actualTitle = driver.getTitle();
        String expectedTitle = "Alchemy LMS – An LMS Application";
        System.out.println("Page Title: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match!");
    }

    @AfterClass
    public void tearDown() {
   
            driver.quit();     
    }
}