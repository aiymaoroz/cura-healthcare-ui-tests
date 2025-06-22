package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.Helper;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginSteps {
    WebDriver driver = Driver.getDriver();
    private LoginPage loginPage = new LoginPage(driver);
    private Helper helper = new Helper(driver);

    @Given("the user is logged out")
    public void the_user_is_logged_out() {
        loginPage.clickBurgerMenu();
        if (loginPage.isLogoutLinkPresent()) {
            loginPage.clickLogoutLink();
        } else {
            assertTrue(loginPage.isLoginLinkPresent(), "Login link should be present when user is logged out");
        }
    }

    @Given("the user clicks the {string} button from navigation bar")
    public void the_user_clicks_the_button_from_navigation_bar(String menuButtonName) {
        loginPage.clickLoginLink();
    }

    @When("the user logs in with valid credentials")
    public void the_user_logs_in_with_valid_credentials() {
        loginPage.login(ConfigReader.getProperty("valid.username"),
                ConfigReader.getProperty("valid.password"));
    }

    @Then("the {string} page should be displayed")
    public void the_page_should_be_displayed(String pageName) {
        assertEquals(ConfigReader.getProperty("curahealthcare.appointment.url"), driver.getCurrentUrl(),
                "Appointment page URL mismatch");
    }

}
