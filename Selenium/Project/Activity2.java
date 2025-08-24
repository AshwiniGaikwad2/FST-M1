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

public class Activity2 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
    	driver = new FirefoxDriver();
    	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    	driver.get("https://alchemy.hguy.co/lms");
    }

    @Test
    public void testHeading() {       
        WebElement heading = driver.findElement(By.className("uagb-ifb-title"));      
        String actualHeading = heading.getText();
        String expectedHeading = "Learn from Industry Experts";
        Assert.assertEquals(actualHeading, expectedHeading, "Heading does not match!");
    }

    @AfterClass
    public void tearDown() {
    	   
        driver.quit();     

    }
}
