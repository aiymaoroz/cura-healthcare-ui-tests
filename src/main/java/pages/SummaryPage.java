package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Map;

/**
 * Page Object for the Summary page.
 */
public class SummaryPage extends BasePage {
    public SummaryPage(WebDriver driver) {
        super(driver);
    }

    private final By facilityField = By.id("facility");
    private final By admissionField = By.id("hospital_readmission");
    private final By programField = By.id("program");
    private final By dateField = By.id("visit_date");
    private final By commentField = By.id("comment");
    private final By summaryHeader = By.xpath("//h2[contains(text(), 'Appointment Confirmation')]");

    public Map<String, String> getAppointmentSummaryDetails() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(facilityField)).perform();
        String actualComment = driver.findElement(commentField).getText();
        if (actualComment.isEmpty()) {
            actualComment = "-";
        }
        return Map.of(
                "facility", driver.findElement(facilityField).getText(),
                "readmission", driver.findElement(admissionField).getText(),
                "program", driver.findElement(programField).getText(),
                "date", driver.findElement(dateField).getText(),
                "comment", actualComment
        );
    }

    public String getSummaryHeaderText() {
        return helper.waitAndHover(summaryHeader).getText();
    }
}
