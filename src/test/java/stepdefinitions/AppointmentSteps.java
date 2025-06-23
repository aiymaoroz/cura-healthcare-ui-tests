package stepdefinitions;

import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import pages.AppointmentPage;
import utilities.Driver;

import static org.testng.Assert.assertTrue;

public class AppointmentSteps {
    WebDriver driver = Driver.getDriver();
    AppointmentPage appointment = new AppointmentPage(driver);

    @Then("the message containing {string} should be displayed on date field")
    public void the_message_containing_should_be_displayed_on_date_field(String message) {
        assertTrue(appointment.getValidationMessageFromVisitDateInputField().contains(message), "Visit date field message mismatch");
    }

    @Then("the user should stay on the appointment page")
    public void the_user_should_stay_on_the_appointment_page() {
        assertTrue(appointment.getHeaderTwoText().contains("Make Appointment"), "User is not on the appointment page");
    }

}
