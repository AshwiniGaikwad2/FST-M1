package Activities;

import static org.testng.Assert.assertEquals;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestActivity10 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Open browser
        driver.get("https://training-support.net/webelements/simple-form");
    }

 
    	public static List<List<String>> readExcel(String filePath) {
    	    List<List<String>> data = new ArrayList<>();
    	    try (FileInputStream file = new FileInputStream(filePath);
    	         XSSFWorkbook workbook = new XSSFWorkbook(file)) {

    	        XSSFSheet sheet = workbook.getSheetAt(0);

    	        for (Row row : sheet) {
    	            List<String> rowData = new ArrayList<>();
    	            for (Cell cell : row) {
    	                switch (cell.getCellType()) {
    	                    case STRING:
    	                        rowData.add(cell.getStringCellValue());
    	                        break;
    	                    case NUMERIC:
    	                        // Convert numeric value to string (for example: 123.0 becomes "123.0")
    	                        rowData.add(String.valueOf(cell.getNumericCellValue()));
    	                        break;
    	                    case BOOLEAN:
    	                        rowData.add(String.valueOf(cell.getBooleanCellValue()));
    	                        break;
    	                    case FORMULA:
    	                        rowData.add(cell.getCellFormula());  // or evaluate it
    	                        break;
    	                    case BLANK:
    	                        rowData.add("");
    	                        break;
    	                    default:
    	                        rowData.add("");
    	                        break;
    	                }
    	            }
    	            data.add(rowData);
    	        }

    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return data;
    	}


    @DataProvider(name = "Events")
    public static Object[][] signUpInfo() {
        String filePath = "src/test/resources/sample.xlsx";
        List<List<String>> data = readExcel(filePath);
        return new Object[][] { 
            { data.get(1) },
            { data.get(2) },
            { data.get(3) }
        };
    }

    @Test(dataProvider = "Events")
    public void registerTest(List<String> rows) throws InterruptedException {
        // Find the input fields and enter text
        WebElement fullName = driver.findElement(By.id("full-name"));
        fullName.sendKeys(rows.get(0));

        // Enter the email
        driver.findElement(By.id("email")).sendKeys(rows.get(1));

        // Enter the Date of the event
        driver.findElement(By.name("event-date")).sendKeys(rows.get(2).replaceAll("\"", ""));

        // Enter additional details
        driver.findElement(By.id("additional-details")).sendKeys(rows.get(3));

        // Click Submit
        driver.findElement(By.xpath("//button[text()='Submit']")).click();
        
        // Confirm booking
        String message = driver.findElement(By.id("action-confirmation")).getText();
        assertEquals(message, "Your event has been scheduled!");

        // Refresh the page
        driver.navigate().refresh();
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
