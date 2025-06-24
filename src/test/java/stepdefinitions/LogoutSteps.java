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

public class LogoutSteps {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    Homepage home = new Homepage(driver);
    AppointmentPage appointment = new AppointmentPage(driver);
    Helper helper = new Helper();

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        home.clickBurgerMenu();
        home.clickLoginLink();
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.loginpage.url"));
        loginPage.login(ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password"));
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.appointment.url"));
    }

    @When("the user clicks the logout button from navigation bar")
    public void the_user_clicks_logout_the_button_from_navigation_bar() {
        appointment.clickBurgerMenu();
        appointment.clickLogoutLink();
        helper.waitForUrlToBe(ConfigReader.getProperty("curahealthcare.homepage.url"));
    }

    @When("the user clicks the Make Appointment button")
    public void the_user_clicks_the_Make_Appointment_button() {
        home.clickMakeAppointmentButton();
    }

    @Then("the login page should be displayed")
    public void the_login_page_should_be_displayed() {
        String expected = ConfigReader.getProperty("curahealthcare.loginpage.url");
        helper.waitForUrlToBe(expected);
        assertEquals(expected, driver.getCurrentUrl(), "Login page URL mismatch");
    }

    @Then("the user should be logged out")
    public void the_user_should_be_logged_out() {
        home.clickBurgerMenu();
        assertTrue(home.isLoginLinkPresent(), "Login link should be present when user is logged out");
        home.clickBurgerMenu();
    }
}
