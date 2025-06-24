package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utilities.Helper;

/**
 * Base page class providing common web page actions and utilities for page objects.
 * Encapsulates common navigation and interaction methods such as clicking menu, login, and logout links.
 * Utilizes a Helper class for element waits and visibility checks.
 */
public class BasePage {
    protected WebDriver driver;
    protected Helper helper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.helper = new Helper();
    }

    private final By burgerMenuButton = By.id("menu-toggle"); // or whatever locator works
    private final By loginLink = By.xpath("//a[contains(text(), 'Login')]");
    private final By logoutLink = By.xpath("//a[contains(text(), 'Logout')]");

    /**
     * Clicks the burger menu button after waiting for its visibility and clickability.
     */
    public void clickBurgerMenu() {
        helper.waitForVisibility(burgerMenuButton);
        helper.waitForClickability(burgerMenuButton).click();
    }

    /**
     * Clicks the logout link after waiting for its visibility and clickability.
     * Scrolls the element into view before clicking.
     */
    public void clickLogoutLink() {
        helper.waitForVisibility(logoutLink);
        WebElement element = helper.waitForClickability(logoutLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    /**
     * Clicks the login link after waiting for its visibility and clickability.
     * Scrolls the element into view before clicking.
     */
    public void clickLoginLink() {
        helper.waitForVisibility(loginLink);
        WebElement element = helper.waitForClickability(loginLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    /**
     * Checks if the login link is present and visible on the page.
     *
     * @return true if the login link is visible, false otherwise
     */
    public boolean isLoginLinkPresent() {
        return helper.isElementVisible(loginLink);
    }
}