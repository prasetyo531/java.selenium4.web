package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"classpath:features"}, glue = {"stepDefinitions"},
        tags = "@Scenario3", monochrome = true, dryRun = false,
        plugin = {"pretty", "html:target/cucumber", "html:target/cucumber-reports.html", "json:target/cucumber.json"})
public class ThreeRunner extends AbstractTestNGCucumberTests {
}
