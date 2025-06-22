package utilities.browser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        return new SafariDriver();
    }
}
