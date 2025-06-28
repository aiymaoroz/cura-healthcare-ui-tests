package stepdefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import models.AppointmentDetails;
import utilities.ConfigReader;

import java.util.List;
import java.util.Map;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AppointmentSteps extends BaseSteps {

    @When("the user fills the appointment form with:")
    public void the_user_fills_the_appointment_form_with(List<AppointmentDetails> appointmentDetails) {
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.appointment.url"));
        appointmentPage.fillAppointmentDetails(appointmentDetails);
    }

    @When("the user fills the date field with {string}")
    public void the_user_fills_the_date_field_with(String date) {
        appointmentPage.fillVisitDateInputField(date);
    }

    @When("the user clicks the Book Appointment button")
    public void the_user_clicks_the_Book_Appointment_button() throws InterruptedException {
        appointmentPage.clickBookAppointmentButton();
    }

    @Then("the Confirmation page should be displayed")
    public void the_Confirmation_page_should_be_displayed() {
        assertTrue(summaryPage.getSummaryHeaderText().contains("Confirmation"),
                "Confirmation page header mismatch");
        assertEquals(driver.getCurrentUrl(),
                ConfigReader.getProperty("curahealthcare.appointment.summary.url"),
                "Confirmation page URL mismatch");
    }

    @Then("the details should match:")
    public void the_details_should_match(List<AppointmentDetails> expectedDetails) {
        Map<String, String> actualDetails = summaryPage.getAppointmentSummaryDetails();
        AppointmentDetails expected = expectedDetails.getFirst();
        assertEquals(actualDetails.get("facility"),
                expected.getFacility() + " CURA Healthcare Center", "Facility mismatch");
        assertEquals(actualDetails.get("readmission"),
                expected.getReadmission(), "Readmission mismatch");
        assertEquals(actualDetails.get("program"),
                expected.getProgram(), "Program mismatch");
        assertEquals(actualDetails.get("date"),
                expected.getDate(), "Visit date mismatch");
        assertEquals(actualDetails.get("comment"),
                expected.getComment(), "Comment mismatch");
    }

    @Then("the message containing {string} should be displayed on date field")
    public void the_message_containing_should_be_displayed_on_date_field(String message) {
        assertTrue(appointmentPage.getValidationMessageFromVisitDateInputField().contains(message),
                "Visit date field message mismatch");
    }

    @Then("the user should stay on the appointment page")
    public void the_user_should_stay_on_the_appointment_page() {
        assertEquals(driver.getCurrentUrl(), ConfigReader.getProperty("curahealthcare.appointment.url"),
                "User is not on Make Appointment page");
        assertTrue(appointmentPage.getAppointmentHeaderText().contains("Make Appointment"),
                "Header text mismatch on Make Appointment page");
    }
}
