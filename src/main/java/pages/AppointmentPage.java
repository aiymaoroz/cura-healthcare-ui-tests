package pages;

import io.cucumber.java.eo.Se;
import models.AppointmentDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AppointmentPage extends BasePage {

    public AppointmentPage(WebDriver driver) {
        super(driver);
    }

    private final By visitDateInputField = By.id("txt_visit_date");
    private final By facilityDropdown = By.id("combo_facility");
    private final By readmissionCheckbox = By.id("chk_hospotal_readmission");
    private final By commentInputField = By.id("txt_comment");

    public void clickBookAppointmentButton() {
        helper.waitForClickability(By.id("btn-book-appointment")).click();
    }

    public String getHeaderTwoText() {
        return driver.findElement(By.xpath("//h2")).getText();
    }

    public String getValidationMessageFromVisitDateInputField() {
        WebElement dateInputField = driver.findElement(visitDateInputField);
        return (String) helper.runJavaScript("return arguments[0].validationMessage;", dateInputField);
    }

    public void fillVisitDateInputField(String date) {
        helper.waitForVisibility(visitDateInputField);
        WebElement dateInputField = driver.findElement(visitDateInputField);
        dateInputField.clear();
        dateInputField.sendKeys(date);
    }

    public void fillAppointmentDetails(List<AppointmentDetails> appointmentDetailsList) {
        AppointmentDetails details = appointmentDetailsList.getFirst();
        Select selectFacility = new Select(driver.findElement(facilityDropdown));
        if (!details.getFacility().equals("-")) selectFacility.selectByValue(details.getFacility()+" CURA Healthcare Center");
        if (details.getReadmission().equalsIgnoreCase("yes")) driver.findElement(readmissionCheckbox).click();
        selectRadioOptionByLabelText(details.getProgram());
        if (!details.getDate().equals("-")) fillVisitDateInputField(details.getDate());
        if (!details.getComment().equals("-")) driver.findElement(commentInputField).sendKeys(details.getComment());
    }

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
