package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.Helper;

public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "txt_visit_date")
    private WebElement visitDateInputField;

    public void clickBookAppointmentButton() {
        helper.waitForClickability(By.id("btn-book-appointment"));
    }

    public String getHeaderTwoText() {
        return driver.findElement(By.xpath("//h2")).getText();
    }

    public String getValidationMessageFromVisitDateInputField() {
        return (String) helper.runJavaScript("return arguments[0].validationMessage;", visitDateInputField);
    }

    public boolean isTextPresentOnAppointmentPageHeader(String text) {
        helper.waitForElementAtXpathToBeVisible(By.xpath("//div[@class='col-xs-12 text-center']"));
        return driver.findElement(By.xpath("//div[@class='col-xs-12 text-center']")).getText().contains(text);
    }
}
