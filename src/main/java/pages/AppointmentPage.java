package pages;

import models.AppointmentDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Page object representing the appointment booking page.
 * Extends BasePage to inherit common web page actions and utilities.
 * Provides methods to interact with appointment form elements, fill details, and submit the form.
 */
public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    private final By visitDateInputField = By.id("txt_visit_date");
    private final By facilityDropdown = By.id("combo_facility");
    private final By readmissionCheckbox = By.id("chk_hospotal_readmission");
    private final By commentInputField = By.id("txt_comment");

    /**
     * Clicks the "Book Appointment" button after waiting for it to be clickable.
     */
    public void clickBookAppointmentButton() {
        helper.waitForClickability(By.id("btn-book-appointment")).click();
    }

    /**
     * Retrieves the text of the header (h2) element on the appointment page.
     *
     * @return the text of the h2 header
     */
    public String getHeaderTwoText() {
        return driver.findElement(By.xpath("//h2")).getText();
    }

    /**
     * Retrieves the browser validation message from the visit date input field.
     *
     * @return the validation message string
     */
    public String getValidationMessageFromVisitDateInputField() {
        WebElement dateInputField = driver.findElement(visitDateInputField);
        return (String) helper.runJavaScript("return arguments[0].validationMessage;", dateInputField);
    }

    /**
     * Fills the visit date input field with the specified date.
     * Waits for the field to be visible, clears it, and enters the date.
     *
     * @param date the date string to enter
     */
    public void fillVisitDateInputField(String date) {
        helper.waitForVisibility(visitDateInputField);
        WebElement dateInputField = driver.findElement(visitDateInputField);
        dateInputField.clear();
        dateInputField.sendKeys(date);
    }

    /**
     * Fills the appointment form fields using the provided appointment details.
     * Selects facility, readmission, program, date, and comment as specified.
     *
     * @param appointmentDetailsList a list containing AppointmentDetails objects
     */
    public void fillAppointmentDetails(List<AppointmentDetails> appointmentDetailsList) {
        AppointmentDetails details = appointmentDetailsList.getFirst();
        Select selectFacility = new Select(driver.findElement(facilityDropdown));
        if (!details.getFacility().equals("-")) selectFacility.selectByValue(details.getFacility()+" CURA Healthcare Center");
        if (details.getReadmission().equalsIgnoreCase("yes")) driver.findElement(readmissionCheckbox).click();
        selectRadioOptionByLabelText(details.getProgram());
        if (!details.getDate().equals("-")) fillVisitDateInputField(details.getDate());
        if (!details.getComment().equals("-")) driver.findElement(commentInputField).sendKeys(details.getComment());
    }

    /**
     * Selects a radio button option by its label text.
     * Throws NoSuchElementException if the label is not found.
     *
     * @param labelText the label text of the radio button to select
     */
    public void selectRadioOptionByLabelText(String labelText) {
        List<WebElement> radioLabels = driver.findElements(By.xpath("//label[@class='radio-inline']"));
        for (WebElement label : radioLabels) {
            if (label.getText().trim().equalsIgnoreCase(labelText.trim())) {
                label.click();
                return;
            }
        }
        throw new NoSuchElementException("Radio button with label '" + labelText + "' not found.");
    }
}