package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

/**
 * BasePage provides common navigation actions available across multiple pages,
 * such as accessing the burger menu, login, and logout links.
 * All other page objects extend this class.
 */
public class BasePage {
    protected WebDriver driver;
    protected Helper helper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.helper = new Helper();
    }

    private final By burgerMenuButton = By.id("menu-toggle");
    private final By loginLink = By.xpath("//a[contains(text(), 'Login')]");
    private final By logoutLink = By.xpath("//a[contains(text(), 'Logout')]");

    public void clickBurgerMenu() {
        helper.safeClick(burgerMenuButton);
    }

    public void clickLogoutLink() {
        helper.safeClick(logoutLink);
    }

    public void clickLoginLink() {
        helper.safeClick(loginLink);
    }

    public boolean isLoginLinkPresent() {
        return helper.isElementVisible(loginLink);
    }
}