package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Login page.
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    private final By usernameTxtBox = By.id("txt-username");
    private final By passwordTxtBox = By.id("txt-password");
    private final By loginBtn = By.id("btn-login");
    private final By loginPageFailureText = By.xpath("//p[@class='lead text-danger']");
    private final By loginPageHeaderText = By.xpath("//div[@class='col-sm-12 text-center']");

    public void login(String username, String password) {
        helper.safeSendKeys(usernameTxtBox, username);
        helper.safeSendKeys(passwordTxtBox, password);
        helper.safeClick(loginBtn);
    }

    public String getFailureTextOnLoginPageHeader() {
        return helper.waitAndHover(loginPageFailureText).getText();
    }

    public String getTextOnLoginPageHeader() {
        return helper.waitAndHover(loginPageHeaderText).getText();
    }
}