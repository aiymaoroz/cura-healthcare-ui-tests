package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page Object for the Homepage.
 */
public class Homepage extends BasePage {
    public Homepage(WebDriver driver) {
        super(driver);
    }

    private final By makeAppointmentButton = By.id("btn-make-appointment");

    public void clickMakeAppointmentButton() {
        helper.safeClick(makeAppointmentButton);
    }
}
