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

public class Activity3 {

   WebDriver driver;
   WebDriverWait wait;
	
   @BeforeClass
   public void setUp() {       
   	driver = new FirefoxDriver();
   	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
   	driver.get("https://alchemy.hguy.co/lms");
   }

   @Test
   public void testInfoBoxTitle() {       
       WebElement firstInfoBoxTitle = driver.findElements(By.className("uagb-ifb-title")).get(1);        
       String actualTitle = firstInfoBoxTitle.getText();      
       String expectedTitle = "Actionable Training";       
       Assert.assertEquals(actualTitle, expectedTitle, "First info box title does not match!");
   }

   @AfterClass
   public void tearDown() {
	   
       driver.quit();     

   }
}