package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Homepage extends BasePage {
    public Homepage(WebDriver driver) {
        super (driver);
    }

    public void clickMakeAppointmentButton() {
        helper.waitForElementWithIdToBeVisible("btn-make-appointment");
        driver.findElement(By.id("btn-make-appointment")).click();
    }
}
