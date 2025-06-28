package pages;

import models.AppointmentDetails;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

/**
 * Encapsulates interactions to fill and submit the appointment booking form.
 */
public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    private final By facilityDropdown = By.id("combo_facility");
    private final By readmissionCheckbox = By.id("chk_hospotal_readmission");
    private final By programRadioButtons = By.xpath("//label[@class='radio-inline']");
    private final By visitDateInputField = By.id("txt_visit_date");
    private final By visitDateLabel = By.xpath("//label[@for='txt_visit_date']");
    private final By commentInputField = By.id("txt_comment");
    private final By commentFieldLabel = By.xpath("//label[@for='txt_comment']");
    private final By bookAppointmentButton = By.id("btn-book-appointment");

    public void clickBookAppointmentButton() {
        helper.safeClick(bookAppointmentButton);
    }

    public String getValidationMessageFromVisitDateInputField() {
        return (String) helper.runJavaScript("return arguments[0].validationMessage;",
                driver.findElement(visitDateInputField));
    }

    /**
     * @param date Date string in format dd/MM/yyyy
     */
    public void fillVisitDateInputField(String date) {
        helper.safeSendKeys(visitDateInputField, date);
        driver.findElement(visitDateLabel).click();
    }

    /**
     * @param appointmentDetailsList Cucumber datatable mapped to AppointmentDetails
     */
    public void fillAppointmentDetails(List<AppointmentDetails> appointmentDetailsList) {
        AppointmentDetails details = appointmentDetailsList.getFirst();
        Select selectFacility = new Select(driver.findElement(facilityDropdown));
        if (!details.getFacility().equals("-")) {
            selectFacility.selectByValue(details.getFacility() + " CURA Healthcare Center");
        }
        if (details.getReadmission().equalsIgnoreCase("Yes")) {
            driver.findElement(readmissionCheckbox).click();
        }
        helper.selectRadioOptionByLabelText(programRadioButtons, details.getProgram());
        if (!details.getDate().equals("-")) {
            fillVisitDateInputField(details.getDate());
        }
        if (!details.getComment().equals("-")) {
            helper.safeSendKeys(commentInputField, details.getComment());
            helper.safeClick(commentFieldLabel);
        }
    }

    public String getAppointmentHeaderText() {
        return helper.waitAndHover(By.xpath("//h2")).getText();
    }
}
