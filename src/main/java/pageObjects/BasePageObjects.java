package pageObjects;

import driver.DriverFactory;
import environments.Config;
import environments.Constants;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;

public class BasePageObjects {

    private WebDriver driver;

    public BasePageObjects() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    public void navigateTo_URL() {
        getDriver().get(Config.get(Constants.BASE_URL));
    }

    public String generateRandomNumber(int length) {
        return RandomStringUtils.randomNumeric(length);
    }

    public String generateRandomString(int length) {
        return RandomStringUtils.randomAlphabetic(length);
    }

    public void sendKeys(By by, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).sendKeys(textToType);
    }

    public void sendKeys(WebElement element, String textToType) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(textToType);
    }

    public void waitFor(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
    }

    public void waitFor(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForWebElementAndClick(By by) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();
    }

    public void waitForWebElementAndClick(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void waitForAlertAndValidateText(String text) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.alertIsPresent());
        String alert_Message_Text = getDriver().switchTo().alert().getText();
        Assert.assertEquals(alert_Message_Text, text);
    }

    public void switchToIframe(WebElement element, WebElement element2) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        // Wait for id iframe to be visible
        wait.until(ExpectedConditions.visibilityOf(element));
        // Switch to the iframe
        DriverFactory.getDriver().switchTo().frame(element);

        wait.until(ExpectedConditions.visibilityOf(element2));
        DriverFactory.getDriver().switchTo().defaultContent();
    }

    public void clickButtonInTable(int rowIndex, WebElement element, By element2) {
        // Wait for the table to be visible
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element));

        // Locate the rows of the table
        List<WebElement> rows = element.findElements(By.tagName("tr"));

        // Check if the row index is valid
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IndexOutOfBoundsException("Row index is out of bounds");
        }

        // Locate the button in the specified row
        WebElement button = rows.get(rowIndex).findElement(element2);
        button.click();
    }

    public void clickElementBeforeUpload(WebElement element) {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(element)); // Wait for the file input to be visible
        element.click(); // Set the file path to the input
    }

    public void waitForNewTab(int tabOpened) {
        WebDriverWait wait = new WebDriverWait(getDriver(), 50);
        wait.until(ExpectedConditions.numberOfWindowsToBe(tabOpened));
    }

    /***
    reporting utility
     ***/
    public void captureScreenshot(Scenario scenario) {
        // Capture screenshot
        if (getDriver() instanceof TakesScreenshot) {
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

            saveScreenshotToFile(screenshot, scenario.getName());
            // Add screenshot to Allure report
            Allure.addAttachment("Screenshot", "image/png", new ByteArrayInputStream(screenshot), ".png");
        } else {
            Allure.step("Driver does not support taking screenshots.");
        }
    }

    public void saveScreenshotToFile(byte[] screenshot, String scenarioName) {
        try {
            // Create target/screenshots directory if it doesn't exist
            Path targetDir = Path.of("target/screenshots");
            if (!Files.exists(targetDir)) {
                Files.createDirectories(targetDir);
            }

            // Create a file for the screenshot
            File screenshotFile = new File(targetDir.toFile(), scenarioName + ".png");
            try (FileOutputStream fos = new FileOutputStream(screenshotFile)) {
                fos.write(screenshot);
            }

            Allure.step("Screenshot saved to: " + screenshotFile.getAbsolutePath());
        } catch (IOException e) {
            Allure.step("Failed to save screenshot: " + e.getMessage());
        }
    }
}
