package stepDefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import pageObjects.BasePageObjects;

import static driver.DriverFactory.cleanupDriver;
import static driver.DriverFactory.getDriver;

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
