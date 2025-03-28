package stepDefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import static driver.DriverFactory.getDriver;
import static driver.DriverFactory.cleanupDriver;

public class MasterHooks {

    private WebDriver driver;

    @Before
    public void setup() {
        getDriver();
    }

    @After
    public void tearDown() {
        cleanupDriver();
    }
}
