package utilities.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * Safari browser implementation of the Browser interface.
 */
public class SafariBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        return new SafariDriver();
    }
}
