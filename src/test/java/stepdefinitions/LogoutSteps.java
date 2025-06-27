package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.ConfigReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LogoutSteps extends BaseSteps {

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        homePage.clickBurgerMenu();
        homePage.clickLoginLink();
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.loginpage.url"));
        loginPage.login(ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password"));
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.appointment.url"));
    }

    @When("the user clicks the logout button from navigation bar")
    public void the_user_clicks_logout_the_button_from_navigation_bar() {
        appointmentPage.clickBurgerMenu();
        appointmentPage.clickLogoutLink();
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.homepage.url"));
    }

    @When("the user clicks the Make Appointment button")
    public void the_user_clicks_the_Make_Appointment_button() {
        homePage.clickMakeAppointmentButton();
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        String expected = ConfigReader.getProperty("curahealthcare.loginpage.url");
        helper.waitForUrlToBe(expected);
        assertEquals(driver.getCurrentUrl(), expected, "Login page URL mismatch");
    }

    @Then("the user should be logged out")
    public void the_user_should_be_logged_out() {
        homePage.clickBurgerMenu();
        assertTrue(homePage.isLoginLinkPresent(), "Login link should be present when user is logged out");
        homePage.clickBurgerMenu();
    }
}
