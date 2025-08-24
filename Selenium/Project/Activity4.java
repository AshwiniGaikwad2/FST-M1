package project;

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

import java.time.Duration;
import java.util.List;

public class Activity4 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {       
       	driver = new FirefoxDriver();
       	wait = new WebDriverWait(driver,Duration.ofSeconds(10));
       	driver.get("https://alchemy.hguy.co/lms");
       }

    @Test
    public void testSecondPopularCourseTitle() {       
        List<WebElement> popularCourses = driver.findElements(By.cssSelector(".ld_course_grid .entry-title"));   
        Assert.assertTrue(popularCourses.size() >= 2, "Less than two courses found!");
        String actualCourseTitle = popularCourses.get(1).getText();
        String expectedCourseTitle = "Email Marketing Strategies";
        Assert.assertEquals(actualCourseTitle, expectedCourseTitle, "Second course title does not match!");
    }

    @AfterClass
    public void tearDown() {
 	   
        driver.quit();     

    }
}