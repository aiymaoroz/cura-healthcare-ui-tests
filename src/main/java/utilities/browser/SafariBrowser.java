package utilities.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Implementation of the Browser interface for Apple Safari.
 * Provides a method to create and return a new Safari WebDriver instance.
 */
public class SafariBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        return new SafariDriver();
    }
}
