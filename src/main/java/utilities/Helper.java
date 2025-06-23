package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helper {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Helper() {
        this.driver = Driver.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitForVisibility(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementAtXpathToBeVisible(By xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
    }

    public void waitForElementWithIdToBeVisible(String id) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(id)));
    }

    public boolean isElementVisible(By locator) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).isDisplayed();
        } catch (TimeoutException | NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    public WebElement waitForClickability(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForUrlToBe(String expectedUrl) {
        wait.until(ExpectedConditions.urlToBe(expectedUrl));
    }

    public void waitForSidebarToBeOpen(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

}
