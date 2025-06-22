package utilities.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.ConfigReader;

public class ChromeBrowser implements Browser {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(ConfigReader.getProperty("headless"))) {
            options.addArguments("--window-size=1920,1080");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.addArguments("--proxy-server=direct://");
            options.addArguments("--proxy-bypass-list=*");
            options.addArguments("disable-extensions");
            options.addArguments("--disable-gpu"); //
            options.addArguments("--headless=new");
        }
        return new ChromeDriver(options);
    }
}
