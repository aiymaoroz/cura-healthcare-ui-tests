package stepdefinitions.hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utilities.ConfigReader;
import utilities.Driver;

/**
 * Cucumber Hooks class for managing setup and teardown actions before and after scenarios.
 * Initializes the WebDriver, navigates to the homepage, captures screenshots, and closes the driver.
 * Used to ensure consistent test environment setup and cleanup for each scenario.
 */
public class Hooks {

    public Hooks() {
    }

    WebDriver driver = Driver.getDriver();

    @Before
    public void setDriver() {
        driver.get(ConfigReader.getProperty("curahealthcare.homepage.url"));
    }

    @After
    public void tearDown(Scenario scenario) {
        Driver.takeScreenshot(scenario);
        Driver.closeDriver();
    }
}