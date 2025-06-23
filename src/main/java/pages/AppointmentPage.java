package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.Helper;

public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTextPresentOnAppointmentPageHeader(String text) {
        helper.waitForElementAtXpathToBeVisible(By.xpath("//div[@class='col-xs-12 text-center']"));
        return driver.findElement(By.xpath("//div[@class='col-xs-12 text-center']")).getText().contains(text);
    }
}
