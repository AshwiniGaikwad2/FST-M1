package project;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Activity8 {


	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
       	driver = new FirefoxDriver();
       	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
       	driver.get("https://alchemy.hguy.co/lms");
       }

   @Test
   public void contactAdmin() throws InterruptedException {       
       WebElement contactMenu = driver.findElement(By.linkText("Contact"));
       contactMenu.click();

       Thread.sleep(2000);
     
       WebElement fullName = driver.findElement(By.id("wpforms-8-field_0"));
       WebElement email = driver.findElement(By.id("wpforms-8-field_1"));
       WebElement subject = driver.findElement(By.id("wpforms-8-field_3"));
       WebElement message = driver.findElement(By.id("wpforms-8-field_2"));
      
       fullName.sendKeys("Ankit Dash");
       email.sendKeys("ankit.dash@ibm.com");
       subject.sendKeys("Automation Message");
       message.sendKeys("This is a test message sent via Selenium automation.");
     
       WebElement submitButton = driver.findElement(By.id("wpforms-submit-8"));
       submitButton.click();

       Thread.sleep(2000); 
      
       WebElement successMessage = driver.findElement(By.cssSelector("div.wpforms-confirmation-container"));
       System.out.println("Submission response: " + successMessage.getText());
   }

   @AfterClass
   public void tearDown() {
  	   
       driver.quit();     

   }
}