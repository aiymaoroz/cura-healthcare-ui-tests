package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page object representing the homepage of the application.
 * Extends BasePage to inherit common web page actions and utilities.
 * Provides methods to interact with elements on the homepage, such as the "Make Appointment" button.
 */

public class Homepage extends BasePage {
    public Homepage(WebDriver driver) {
        super(driver);
    }

    /**
     * Clicks the "Make Appointment" button on the page.
     * Waits for the button to be visible before clicking it.
     */
    public void clickMakeAppointmentButton() {
        WebElement makeAppointmentButton = helper.waitForVisibility(By.id("btn-make-appointment"));
        makeAppointmentButton.click();
    }
}
