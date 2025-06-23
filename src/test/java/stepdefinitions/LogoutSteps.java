package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.AppointmentPage;
import pages.Homepage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import static org.testng.Assert.assertTrue;

public class LogoutSteps {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    Homepage home = new Homepage(driver);
    AppointmentPage appointment = new AppointmentPage(driver);

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        home.clickBurgerMenu();
        if (home.isLoginLinkPresent()) {
            home.clickLoginLink();
            loginPage.login(ConfigReader.getProperty("valid.username"),
                    ConfigReader.getProperty("valid.password"));
            loginPage.clickBurgerMenu();
        }
        assertTrue(home.isLogoutLinkPresent(), "Logout link should be present when user is logged in");
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonName) {
        if (buttonName.equalsIgnoreCase("Make Appointment")) {
            home.clickMakeAppointmentButton();
        } else if (buttonName.equalsIgnoreCase("Book Appointment")) {
            appointment.clickBookAppointmentButton();
        } else {
            throw new IllegalArgumentException("Button name not recognized: " + buttonName);
        }
    }

    @Then("the user should be logged out")
    public void the_user_should_be_logged_out() {
        home.clickBurgerMenu();
        assertTrue(home.isLoginLinkPresent(), "Login link should be present when user is logged out");
    }

}
