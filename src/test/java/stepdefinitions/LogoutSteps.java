package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BasePage;
import pages.Homepage;
import pages.LoginPage;
import utilities.ConfigReader;
import utilities.Driver;

import static org.testng.Assert.assertTrue;

public class LogoutSteps {
    WebDriver driver = Driver.getDriver();
    LoginPage loginPage = new LoginPage(driver);
    Homepage home = new Homepage(driver);
    BasePage base = new BasePage(driver);

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        home.clickBurgerMenu();
        if (home.isLoginLinkPresent()) {
            home.clickLoginLink();
            loginPage.login(ConfigReader.getProperty("valid.username"),
                    ConfigReader.getProperty("valid.password"));
            driver.get(ConfigReader.getProperty("curahealthcare.homepage.url"));
            home.clickBurgerMenu();
        } else {
            assertTrue(home.isLogoutLinkPresent(), "Logout link should be present when user is logged in");
        }
    }

    @When("the user clicks the {string} button")
    public void the_user_clicks_the_button(String buttonName) {
        home.clickMakeAppointmentButton();
    }


}
