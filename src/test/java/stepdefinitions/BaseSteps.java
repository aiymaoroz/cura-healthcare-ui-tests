package stepdefinitions;

import org.openqa.selenium.WebDriver;
import pages.*;
import utilities.Driver;
import utilities.Helper;

/**
 * BaseSteps serves as a base class for step definition classes.
 */
public class BaseSteps {
    protected WebDriver driver = Driver.getDriver();
    protected Helper helper = new Helper();
    protected AppointmentPage appointmentPage = new AppointmentPage(driver);
    protected Homepage homePage = new Homepage(driver);
    protected LoginPage loginPage = new LoginPage(driver);
    protected SummaryPage summaryPage = new SummaryPage(driver);
}
