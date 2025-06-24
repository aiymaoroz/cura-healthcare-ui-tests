package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Homepage extends BasePage {
    public Homepage(WebDriver driver) {
        super(driver);
    }

    public void clickMakeAppointmentButton() {
        WebElement makeAppointmentButton = helper.waitForVisibility(By.id("btn-make-appointment"));
        makeAppointmentButton.click();
    }
}
