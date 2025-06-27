package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestNG runner for Cucumber feature files with specified tags and reports.
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
