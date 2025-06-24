package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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

    public void clickBurgerMenu() {
        helper.waitForVisibility(burgerMenuButton);
        helper.waitForClickability(burgerMenuButton).click();
    }

    public void clickLogoutLink() {
        helper.waitForVisibility(logoutLink);
        WebElement element = helper.waitForClickability(logoutLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void clickLoginLink() {
        helper.waitForVisibility(loginLink);
        WebElement element = helper.waitForClickability(loginLink);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public boolean isLoginLinkPresent() {
        return helper.isElementVisible(loginLink);
    }

}
