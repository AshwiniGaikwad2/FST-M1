package project;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Activity7 {

	WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
       	driver = new FirefoxDriver();
       	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
       	driver.get("https://alchemy.hguy.co/lms");
       }

 @Test
 public void countCourses() throws InterruptedException {
     WebElement allCoursesMenu = driver.findElement(By.linkText("All Courses"));
     allCoursesMenu.click();
     Thread.sleep(2000);
     List<WebElement> courses = driver.findElements(By.cssSelector("div.ld_course_grid"));
     System.out.println("Total number of courses: " + courses.size());
 }

 @AfterClass
 public void tearDown() {
	   
     driver.quit();     

 }
}


