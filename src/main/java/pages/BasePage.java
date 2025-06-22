package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Helper;

public class BasePage {
    protected WebDriver driver;
    protected Helper helper;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.helper = new Helper(driver);
    }

    @FindBy(id = "menu-toggle")
    private WebElement burgerMenuButton;

    @FindBy(xpath = "//a[contains(text(), 'Home')]")
    private WebElement menuHomeLink;

    public boolean isLoginLinkPresent() {
        return helper.isElementPresent(By.xpath("//a[contains(text(), 'Login')]"));
    }

    public void clickLoginLink() {
        if (isLoginLinkPresent()) {
            driver.findElement(By.xpath("//a[contains(text(), 'Login')]")).click();
        } else {
            throw new NoSuchElementException("Login link not found");
        }
    }

    public void clickBurgerMenu() {
        burgerMenuButton.click();
    }

    public boolean isLogoutLinkPresent() {
        return helper.isElementPresent(By.xpath("//a[contains(text(), 'Logout')]"));
    }

    public void clickLogoutLink() {
        if (isLogoutLinkPresent()) {
            driver.findElement(By.xpath("//a[contains(text(), 'Logout')]")).click();
        } else {
            throw new NoSuchElementException("Logout link not found");
        }
    }


}
