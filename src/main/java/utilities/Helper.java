package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class providing helper methods for common Selenium WebDriver operations.
 * <p>
 * Encapsulates waiting for element visibility, clickability, URL changes, and executing JavaScript.
 * Uses explicit waits to improve test reliability and reduce flakiness.
 */
public class Helper {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Helper() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    /**
     * Waits until the element located by the given locator is visible on the page.
     *
     * @param locator the By locator of the element to wait for
     * @return the visible WebElement
     */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits until the element located by the given locator is clickable.
     *
     * @param locator the By locator of the element to wait for
     * @return the clickable WebElement
     */
    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Checks if the element located by the given locator is visible on the page.
     *
     * @param locator the By locator of the element to check
     * @return true if the element is visible, false otherwise
     */
    public boolean isElementVisible(By locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return element.isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    /**
     * Waits until the current URL matches the expected URL.
     *
     * @param expectedUrl the URL to wait for
     */
    public void waitForUrlToBe(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    /**
     * Executes the given JavaScript in the context of the current page.
     *
     * @param script the JavaScript code to execute
     * @param args   the arguments to pass to the script
     * @return the result of the script execution
     */
    public Object runJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }
}