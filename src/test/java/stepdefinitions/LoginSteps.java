package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.Homepage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private Homepage home = new Homepage(driver);
    private Helper helper = new Helper();

    @Given("the user is logged out")
    public void the_user_is_logged_out() {
        home.clickBurgerMenu();
        assertTrue(home.isLoginLinkPresent(), "Login link should be present when user is logged out");
        home.clickBurgerMenu();
    }

    @When("the user clicks the login button from navigation bar")
    public void the_user_clicks_login_the_button_from_navigation_bar() {
        home.clickBurgerMenu();
        home.clickLoginLink();
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.loginpage.url"));
        assertTrue(loginPage.getTextOnLoginPageHeader().contains("Login"), "Login page header text mismatch");
    }


    @When("the user logs in with {string} username {string} and password {string}")
    public void the_user_logs_in_with_username_and_password(String validOrNot, String username, String password) {
        if (validOrNot.equalsIgnoreCase("valid")) {
            loginPage.login(ConfigReader.getProperty(username),
                    ConfigReader.getProperty(password));
        } else {
            loginPage.login(username, password);
        }
    }

    @Then("the appointment page should be displayed")
    public void the_appointment_page_should_be_displayed() {
        String expected = ConfigReader.getProperty("curahealthcare.appointment.url");
        helper.waitForUrlToBe(expected);
        assertEquals(expected, driver.getCurrentUrl(), "Appointment page URL mismatch");
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
        assertTrue(loginPage.getTextOnFailedLoginPageHeader().contains(errorMessage),
                "Error message mismatch on login page header");

    }
}
