package stepDefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.qameta.allure.Allure;

import static driver.DriverFactory.getDriver;
import static driver.DriverFactory.cleanupDriver;

public class MasterHooks {

    @Before
    public void setup() {
        getDriver();
        Allure.step("Starting test setup for Selenium Grid.");
    }

    @After
    public void tearDown() {
        // Log the result of the test in Allure
        Allure.step("Tearing down the test and cleaning up WebDriver.");

        // Cleanup WebDriver
        cleanupDriver();
    }
}
