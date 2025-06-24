package utilities.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utilities.ConfigReader;

/**
 * Implementation of the Browser interface for Mozilla Firefox.
 * Configures and creates a new Firefox WebDriver instance with custom options,
 * including support for headless mode based on configuration properties.
 */
public class FirefoxBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--headless");
        }
        return new FirefoxDriver(options);
    }
}
