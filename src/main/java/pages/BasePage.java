package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.Helper;

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
    private final By sidebarWrapper = By.id("sidebar-wrapper");


    public void clickBurgerMenu() {
        helper.waitForVisibility(burgerMenuButton);
        helper.waitForClickability(burgerMenuButton).click();
        helper.waitForVisibility(sidebarWrapper);
        helper.waitForElementClassContains(sidebarWrapper, "active");
    }

    public void clickLoginLink() {
        if (!isLoginLinkPresent()) {
            clickBurgerMenu();
            helper.waitForClickability(loginLink).click();
        }
        helper.waitForClickability(loginLink).click();
    }

    public void clickLogoutLink() {
        if (!isLogoutLinkPresent()) {
            clickBurgerMenu();
            helper.waitForClickability(logoutLink).click();
        }
        helper.waitForClickability(logoutLink).click();
    }

    public boolean isLoginLinkPresent() {
        return helper.isElementVisible(loginLink);
    }

    public boolean isLogoutLinkPresent() {
        return helper.isElementVisible(logoutLink);
    }

}
