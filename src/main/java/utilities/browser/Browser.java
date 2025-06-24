package utilities.browser;

import org.openqa.selenium.WebDriver;

/**
 * Interface representing a browser abstraction for WebDriver creation.
 * Implementations of this interface should provide a method to instantiate and configure a WebDriver instance
 * for a specific browser type.
 */
public interface Browser {
    WebDriver createDriver();
}
