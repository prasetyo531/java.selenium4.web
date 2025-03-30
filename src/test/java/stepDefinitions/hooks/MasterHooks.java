package stepDefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pageObjects.BasePageObjects;

import java.io.ByteArrayInputStream;

import static driver.DriverFactory.*;
import static pageObjects.BasePageObjects.*;

public class MasterHooks {

    @Before
    public void setup() {
        getDriver();
        Allure.step("Starting test setup for Selenium Grid.");
    }

    @After
    public void tearDown(Scenario scenario) {
        // Log the result of the test in Allure
        if (scenario.isFailed()) {
            Allure.step("Scenario failed: " + scenario.getName());
            BasePageObjects basePageObjects = new BasePageObjects();
            basePageObjects.captureScreenshot(scenario);
        } else {
            Allure.step("Scenario passed: " + scenario.getName());
        }

        // Cleanup WebDriver
        cleanupDriver();
    }
}
