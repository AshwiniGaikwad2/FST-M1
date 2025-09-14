package Activities;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class ToDoAppTest extends BaseTest {

    @Test(priority = 1)
    public void addTasks() {
        String[][] tasks = {
                {"Complete Activity 1", "High"},
                {"Complete Activity 2", "Medium"},
                {"Complete Activity 3", "Low"}
        };

        for (String[] task : tasks) {
            driver.findElement(AppiumBy.accessibilityId("Add Task")).click();
            driver.findElement(AppiumBy.id("taskTitle")).sendKeys(task[0]);
            driver.findElement(AppiumBy.id("priorityDropdown")).click();
            driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\\\"" + task[1] + "\")")).click();
            driver.findElement(AppiumBy.accessibilityId("Save")).click();
        }

        List<WebElement> taskTitles = driver.findElements(AppiumBy.id("taskTitleView"));
        Assert.assertEquals(taskTitles.size(), 3, "All three tasks should be added");
    }

    @Test(priority = 2)
    public void editFirstTask_AddDeadline() {
        WebElement firstTask = driver.findElements(AppiumBy.id("taskContainer")).get(0);
        longPress(firstTask, Duration.ofSeconds(1));

        driver.findElement(AppiumBy.id("deadlineField")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\\\"Sat\\\")")).click();
        driver.findElement(AppiumBy.accessibilityId("OK")).click();
        driver.findElement(AppiumBy.accessibilityId("Save")).click();

        WebElement deadlineView = driver.findElements(AppiumBy.id("deadlineView")).get(0);
        Assert.assertTrue(deadlineView.getText().contains("Saturday"));
    }

    @Test(priority = 3)
    public void completeTasksAndCheckCompletedSection() {      
        List<WebElement> checkboxes = driver.findElements(AppiumBy.id("taskCheckbox"));
        checkboxes.get(0).click();
        checkboxes.get(1).click();
       
        WebElement thirdTask = driver.findElements(AppiumBy.id("taskContainer")).get(2);
        longPress(thirdTask, Duration.ofMillis(800));

        WebElement slider = driver.findElement(AppiumBy.id("progressBar"));
        dragSliderTo(slider, 0.5); // 50%

        driver.findElement(AppiumBy.accessibilityId("Save")).click();
      
        driver.findElement(AppiumBy.accessibilityId("Options")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\\\"Completed tasks\\\")")).click();

        List<WebElement> completedTasks = driver.findElements(AppiumBy.id("taskTitleView"));
        Assert.assertEquals(completedTasks.size(), 2, "Only 2 tasks should be completed");
    }

    private void longPress(WebElement element, Duration hold) {
        var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        var rect = element.getRect();
        int cx = rect.getX() + rect.getWidth() / 2;
        int cy = rect.getY() + rect.getHeight() / 2;

        Sequence seq = new Sequence(finger, 0);
        seq.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), cx, cy));
        seq.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
		// seq.addAction(finger.createPause(hold));
        seq.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(seq));
    }

    private void dragSliderTo(WebElement slider, double fraction) {
        fraction = Math.max(0.0, Math.min(1.0, fraction));
        var finger = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        var rect = slider.getRect();
        int startX = rect.getX() + 10;
        int y = rect.getY() + rect.getHeight() / 2;
        int endX = rect.getX() + (int) (rect.getWidth() * fraction);

        Sequence seq = new Sequence(finger, 0);
        seq.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        seq.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        seq.addAction(finger.createPointerMove(Duration.ofMillis(600), PointerInput.Origin.viewport(), endX, y));
        seq.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(seq));
    }
}