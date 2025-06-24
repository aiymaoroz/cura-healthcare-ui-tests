package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.AppointmentDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.AppointmentPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Helper;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AppointmentSteps {
    WebDriver driver = Driver.getDriver();
    AppointmentPage appointment = new AppointmentPage(driver);
    Helper helper = new Helper();
    private List<AppointmentDetails> actualDetails;

    @When("the user fills the appointment form with:")
    public void the_user_fills_the_appointment_form_with(List<AppointmentDetails> appointmentDetails) {
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.appointment.url"));
        appointment.fillAppointmentDetails(appointmentDetails);
        this.actualDetails = appointmentDetails;
    }

    @When("the user fills the date field with {string}")
    public void the_user_fills_the_date_field_with(String date) {
        appointment.fillVisitDateInputField(date);
    }

    @When("the user clicks the Book Appointment button")
    public void the_user_clicks_the_Make_Appointment_button() {
        driver.findElement(By.id("txt_visit_date")).sendKeys(Keys.TAB);
        appointment.clickBookAppointmentButton();
    }

    @Then("the Confirmation page should be displayed")
    public void the_Confirmation_page_should_be_displayed() {
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.appointment.summary.url"));
        assertTrue(appointment.getHeaderTwoText().contains("Confirmation"));
    }

    @Then("the details should match:")
    public void the_details_should_match(List<AppointmentDetails> expectedDetails) {
        AppointmentDetails actualDetails = this.actualDetails.getFirst();
        AppointmentDetails expected = expectedDetails.getFirst();

        assertTrue(actualDetails.getFacility().contains(expected.getFacility()), "Facility mismatch");
        assertTrue(actualDetails.getReadmission().equalsIgnoreCase(expected.getReadmission()), "Readmission mismatch");
        assertTrue(actualDetails.getProgram().equalsIgnoreCase(expected.getProgram()), "Program mismatch");
        assertEquals(expected.getDate(), actualDetails.getDate(), "Visit date mismatch");
        assertEquals(expected.getComment(), actualDetails.getComment(), "Comment mismatch");
    }

    @Then("the message containing {string} should be displayed on date field")
    public void the_message_containing_should_be_displayed_on_date_field(String message) {
        assertTrue(appointment.getValidationMessageFromVisitDateInputField().contains(message), "Visit date field message mismatch");
    }

    @Then("the user should stay on the appointment page")
    public void the_user_should_stay_on_the_appointment_page() {
        assertEquals(ConfigReader.getProperty("curahealthcare.appointment.url"), driver.getCurrentUrl(), "User is not on Make Appointment page");
    }

}
