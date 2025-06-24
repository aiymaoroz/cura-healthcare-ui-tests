package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object representing the login page of the application.
 * Extends BasePage to inherit common web page actions and utilities.
 * Provides methods to interact with login form elements and retrieve header text.
 */
public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "txt-username")
    private WebElement usernameTxtBox;

    @FindBy(id = "txt-password")
    private WebElement passwordTxtBox;

    @FindBy(id = "btn-login")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@class='col-sm-12 text-center']")
    private WebElement loginPageHeader;

    /**
     * Logs in using the provided username and password.
     * Enters the credentials and clicks the login button.
     *
     * @param username the username to enter
     * @param password the password to enter
     */
    public void login(String username, String password) {
        usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        loginBtn.click();
    }

    /**
     * Retrieves the header text displayed on the failed login page.
     * Waits for the error message to be visible before retrieving the text.
     *
     * @return the header text on the failed login page
     */
    public String getTextOnFailedLoginPageHeader() {
        helper.waitForVisibility(By.xpath("//div[@class='col-sm-12 text-center']//p[@class='lead text-danger']"));
        WebElement header = driver.findElement(By.xpath("//div[@class='col-sm-12 text-center']"));
        return header.getText();
    }

    /**
     * Retrieves the header text displayed on the login page.
     * Waits for the header to be visible before retrieving the text.
     *
     * @return the header text on the login page
     */
    public String getTextOnLoginPageHeader() {
        helper.waitForVisibility(By.xpath("//div[@class='col-sm-12 text-center']"));
        WebElement header = driver.findElement(By.xpath("//div[@class='col-sm-12 text-center']"));
        return header.getText();
    }
}