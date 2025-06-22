package stepdefinitions.hooks;

    import io.cucumber.java.After;
    import io.cucumber.java.Before;
    import io.cucumber.java.Scenario;
    import org.openqa.selenium.WebDriver;
    import utilities.ConfigReader;
    import utilities.Driver;

    /**
     * Cucumber hooks for setting up and tearing down WebDriver before and after scenarios.
     * <p>
     * - The @Before hook navigates to the homepage before each scenario.
     * - The @After hook takes a screenshot on failure and closes the WebDriver after each scenario.
     */
    public class Hooks {
        /**
         * Default constructor for the Hooks class.
         */
        public Hooks() {
        }

        /**
         * The WebDriver instance used for browser interactions.
         * <p>
         * Initialized using the Driver utility class.
         */
        WebDriver driver = Driver.getDriver();

        /**
         * Cucumber @Before hook.
         * <p>
         * Navigates to the homepage URL specified in the configuration before each scenario.
         */
        @Before
        public void setDriver() {
            driver.get(ConfigReader.getProperty("curahealthcare.homepage"));
        }

        /**
         * Cucumber @After hook.
         * <p>
         * Takes a screenshot if the scenario fails and closes the WebDriver after each scenario.
         *
         * @param scenario the current Cucumber scenario
         */
        @After
        public void tearDown(Scenario scenario) {
            Driver.takeScreenshot(scenario);
            Driver.closeDriver();
        }
    }