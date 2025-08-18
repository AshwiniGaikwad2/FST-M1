package stepDefinition;

import org.junit.jupiter.api.Assertions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps extends BaseClass {
    @Given("the user is on the login page")
    public void openPage() {
        driver.get("https://training-support.net/webelements/login-form");
        Assertions.assertEquals("Selenium: Login Form", driver.getTitle());
    }

    @When("the user enters username and password")
    public void enterCredentials() {
        
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("password");
    }

    @When("the user enters {string} and {string}")    
    public void enterParams(String username ,String password) {

        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }
    
    @And("clicks the submit button")
    public void clickSubmit() {
        
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
    }

    @Then("get the confirmation message and verify it")
    public void confirmMessage() {
        
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h2.mt-5"), "Welcome"));
        String message = driver.findElement(By.cssSelector("h2.mt-5")).getText();
        Assertions.assertEquals("Welcome Back, Admin!", message);
    }
    
    @Then("get the confirmation message and verify it as {string}")
    public void verifyLoginformParam(String expectedmsg) {
        
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.cssSelector("h2.mt-5"), "Welcome"));
        String message = driver.findElement(By.cssSelector("h2.mt-5")).getText();
        Assertions.assertEquals(expectedmsg, message);
    }
    
    @Then("get the confirmation text and verify message as {string}")
    public void verifyInvalid(String expectedmsg) {
    	String message = "NOT FOUND";
        if (expectedmsg.contains("Invalid")) {
            message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2#subheading"))).getText();
        } else {
            message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.text-center"))).getText();
        }
        
        Assertions.assertEquals(expectedmsg, message);
    }
}