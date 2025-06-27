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
 * Driver utility class for managing WebDriver instances.
 * Implements a singleton pattern to ensure only one WebDriver instance is active at a time.
 * Provides methods to initialize the driver based on configuration, take screenshots on test failure,
 * and properly close the driver after use.
 */
public class Driver {
    private static WebDriver driver;

    private Driver() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            String browser = Optional.ofNullable(ConfigReader.getProperty("browser")).orElse("chrome").toLowerCase();
            Browser browserStrategy = switch (browser) {
                case "firefox" -> new FirefoxBrowser();
                case "safari" -> new SafariBrowser();
                default -> new ChromeBrowser();
            };
            driver = browserStrategy.createDriver();
        }
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    public static void takeScreenshot(Scenario scenario) {
        if (scenario.isFailed()) {
            final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}