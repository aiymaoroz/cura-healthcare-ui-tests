package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void login(String username, String password) {
        usernameTxtBox.sendKeys(username);
        passwordTxtBox.sendKeys(password);
        loginBtn.click();
    }

    public boolean isTextPresentOnLoginPageHeader(String text) {
        helper.waitForElementAtXpathToBeVisible(By.xpath("//div[@class='col-sm-12 text-center']"));
        return loginPageHeader.getText().contains(text);
    }
}
