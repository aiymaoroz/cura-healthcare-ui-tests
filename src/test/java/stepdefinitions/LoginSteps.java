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
import utilities.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private AppointmentPage appointmentPage = new AppointmentPage(driver);
    private Homepage home = new Homepage(driver);
    private Helper helper = new Helper();

    @Given("the user is logged out")
    public void the_user_is_logged_out() {
        loginPage.clickBurgerMenu();
        if (loginPage.isLogoutLinkPresent()) {
            loginPage.clickLogoutLink();
            loginPage.clickBurgerMenu();
        }
        assertTrue(loginPage.isLoginLinkPresent(), "Login link should be present when user is logged out");
    }

    @When("the user clicks the {string} button from navigation bar")
    public void the_user_clicks_the_button_from_navigation_bar(String menuButtonName) {
        if (menuButtonName.equalsIgnoreCase("Login")) {
            loginPage.clickLoginLink();
            assertTrue(loginPage.isTextPresentOnLoginPageHeader(menuButtonName));
        } else if (menuButtonName.equalsIgnoreCase("Logout")) {
            loginPage.clickLogoutLink();
        } else {
            throw new IllegalArgumentException("Invalid menu button name: " + menuButtonName);
        }
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

    @Then("the {string} page should be displayed")
    public void the_page_should_be_displayed(String pageName) {
        switch (pageName.toLowerCase()) {
            case "make appointment" -> {
                String expected = ConfigReader.getProperty("curahealthcare.appointment.url");
                helper.waitForUrlToBe(expected);
                assertEquals(expected, driver.getCurrentUrl(), "Appointment page URL mismatch");
            }

            case "login" -> {
                String expected = ConfigReader.getProperty("curahealthcare.loginpage.url");
                helper.waitForUrlToBe(expected);
                assertEquals(expected, driver.getCurrentUrl(), "Login page URL mismatch");
            }

            case "confirmation" -> {
                String expected = ConfigReader.getProperty("curahealthcare.appointment.summary.url");
                helper.waitForUrlToBe(expected);
                assertEquals(expected, driver.getCurrentUrl(), "Confirmation page URL mismatch");
                assertTrue(appointmentPage.isTextPresentOnAppointmentPageHeader("Appointment Confirmation"));
            }

            default -> throw new IllegalArgumentException("Invalid page name: " + pageName);
        }
    }

    @Then("an error message {string} should be displayed")
    public void an_error_message_should_be_displayed(String errorMessage) {
        assertTrue(loginPage.isTextPresentOnLoginPageHeader(errorMessage),
                "Error message mismatch on login page header");
    }
}
