package utilities.browser;

import org.openqa.selenium.WebDriver;

/**
 * Browser interface for creating configured WebDriver instances.
 */
public interface Browser {
    WebDriver createDriver();
}
