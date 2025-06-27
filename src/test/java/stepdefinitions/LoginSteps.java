package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ConfigReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps extends BaseSteps {

    @Given("the user is logged out")
    public void the_user_is_logged_out() {
        homePage.clickBurgerMenu();
        assertTrue(homePage.isLoginLinkPresent(), "Login link should be present when user is logged out");
        homePage.clickBurgerMenu();
    }

    @When("the user clicks the login button from navigation bar")
    public void the_user_clicks_login_the_button_from_navigation_bar() {
        homePage.clickBurgerMenu();
        homePage.clickLoginLink();
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
        assertEquals(driver.getCurrentUrl(), expected, "Appointment page URL mismatch");
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
        assertTrue(loginPage.getFailureTextOnLoginPageHeader().contains(errorMessage),
                "Error message mismatch on login page header");
    }
}
