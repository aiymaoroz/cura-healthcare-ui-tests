package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * Test runner class for executing Cucumber feature files using TestNG.
 * Configures feature locations, step definitions, tags, and reporting plugins.
 * Inherits from AbstractTestNGCucumberTests to integrate Cucumber with TestNG.
 */
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepdefinitions", "stepdefinitions.hooks"},
        tags = "(@Login or @Booking) and not @KnownBug",
        plugin = {
                "pretty",
                "html:target/CucumberReports/cucumber-reports.html",
                "json:target/CucumberReports/report.json",
                "junit:target/CucumberReports/junit-report.xml"
        },
        monochrome = true
)
public class TestRunner extends AbstractTestNGCucumberTests {
}
