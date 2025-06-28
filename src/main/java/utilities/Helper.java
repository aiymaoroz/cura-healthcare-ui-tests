package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Utility class providing helper methods for common operations.
 */
public class Helper {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Helper() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    /**
     * @param locator the By locator of the element to wait for
     * @return the visible WebElement
     */
    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * @param locator the By locator of the element to wait for
     * @return the clickable WebElement
     */
    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
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
     * @param expectedUrl the URL to wait for
     */
    public void waitForUrlToBe(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    /**
     * @param script the JavaScript code to execute
     * @param args   the arguments to pass to the script
     * @return the result of the script execution
     */
    public Object runJavaScript(String script, Object... args) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return jsExecutor.executeScript(script, args);
    }

    /**
     * Scrolls the element into view and waits until it is clickable.
     * If a standard click is intercepted, attempts to click using JavaScript.
     *
     * @param locator the By locator of the element to click
     */
    public void safeClick(By locator) {
        WebElement element = driver.findElement(locator);
        try {
            runJavaScript("arguments[0].scrollIntoView(true);", element);
            new WebDriverWait(driver, Duration.ofSeconds(15))
                    .until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException e) {
            runJavaScript("arguments[0].click();", element);
        }
    }

    /**
     * Waits for the element to be clickable, scrolls it into view, clears it, and sends the provided text.
     * If the element is not interactable, sets the value using JavaScript.
     *
     * @param locator the By locator of the element to send keys to
     * @param text    the text to send to the element
     */
    public void safeSendKeys(By locator, String text) {
        try {
            WebElement element = waitForClickability(locator);
            runJavaScript("arguments[0].scrollIntoView(true);", element);
            element.clear();
            element.sendKeys(text);
        } catch (ElementNotInteractableException e) {
            WebElement element = driver.findElement(locator);
            runJavaScript("arguments[0].scrollIntoView(true);", element);
            runJavaScript("arguments[0].value = arguments[1];", element, text);
        }
    }

    /**
     * Finds all elements matching the given locator, compares their text to the provided label,
     * and clicks the matching label. Throws an exception if no match is found.
     *
     * @param listLocator the By locator for the list of radio button labels
     * @param labelText   the label text to match and select
     * @throws NoSuchElementException if no radio button with the given label is found
     */
    public void selectRadioOptionByLabelText(By listLocator, String labelText) {
        List<WebElement> radioLabels = driver.findElements(listLocator);
        for (WebElement label : radioLabels) {
            if (label.getText().trim().equalsIgnoreCase(labelText.trim())) {
                label.click();
                return;
            }
        }
        throw new NoSuchElementException("Radio button with label '" + labelText + "' not found.");
    }

    /**
     * @param locator the By locator of the element to hover over
     * @return the WebElement after hovering
     */
    public WebElement waitAndHover(By locator) {
        WebElement element = waitForVisibility(locator);
        new Actions(driver).moveToElement(element).perform();
        return element;
    }

    /**
     * Clicks on the element located by the given locator using JavaScript.
     *
     * @param locator the By locator of the element to click
     */
    public void jsClick(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}