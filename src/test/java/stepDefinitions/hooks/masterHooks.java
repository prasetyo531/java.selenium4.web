package stepDefinitions.base;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import static driver.driverFactory.getDriver;
import static driver.driverFactory.cleanupDriver;

public class masterHooks {
    @Before
    public void setup() {
        getDriver();
    }

    @After
    public void tearDown() {
        cleanupDriver();
    }
}
