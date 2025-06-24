package utilities.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ConfigReader;

import java.util.Arrays;
import java.util.Map;

/**
 * Implementation of the Browser interface for Chrome.
 * Configures and creates a new Chrome WebDriver instance with custom options,
 * including incognito mode, disabling password manager, and handling headless mode.
 */
public class ChromeBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("--disable-password-manager-reauthentication");
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.addArguments("--disable-save-password-bubble");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Arrays.asList("enable-automation", "load-extension"));
        options.setExperimentalOption("prefs", Map.of(
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false
        ));
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--window-size=1920,1080");
            options.addArguments("--proxy-server=direct://");
            options.addArguments("--proxy-bypass-list=*");
            options.addArguments("disable-extensions");
            options.addArguments("--disable-gpu"); //
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }
}