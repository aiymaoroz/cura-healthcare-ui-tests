package utilities;

import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utilities.browser.Browser;
import utilities.browser.ChromeBrowser;
import utilities.browser.FirefoxBrowser;
import utilities.browser.SafariBrowser;

import java.time.Duration;
import java.util.Optional;

/**
 * Utility class for managing the Selenium WebDriver instance.
 * <p>
 * Implements a singleton pattern to ensure only one WebDriver instance is used throughout the test run.
 * Provides methods to get the driver, take screenshots on test failure, and close the driver.
 */
public class Driver {
    /**
     * The singleton WebDriver instance.
     */
    private static WebDriver driver;

    /**
     * Private constructor to prevent instantiation.
     */
    private Driver() {
    }

    /**
     * Returns the singleton WebDriver instance.
     * <p>
     * Initializes the driver if it does not exist, using the browser type specified in the configuration.
     * Supported browsers: chrome (default), firefox, safari.
     * Sets timeouts and maximizes the browser window.
     *
     * @return the WebDriver instance
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = Optional.ofNullable(ConfigReader.getProperty("browser")).orElse("chrome").toLowerCase();
            System.out.println("Launching browser: " + browser);
            Browser browserStrategy = switch (browser) {
                case "firefox" -> new FirefoxBrowser();
                case "safari" -> new SafariBrowser();
                default -> new ChromeBrowser();
            };
            driver = browserStrategy.createDriver();
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().window().maximize();
        }
        return driver;
    }

    /**
     * Takes a screenshot if the scenario has failed and attaches it to the Cucumber report.
     *
     * @param scenario the current Cucumber scenario
     */
    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }

    /**
     * Closes the WebDriver instance and sets it to null.
     * Should be called after test to clean up resources.
     */
    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}