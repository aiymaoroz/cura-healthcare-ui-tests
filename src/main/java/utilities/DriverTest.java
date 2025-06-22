package utilities;

import org.openqa.selenium.WebDriver;

public class DriverTest {
    public static void main(String[] args) {
        WebDriver driver = Driver.getDriver();
        driver.get("https://katalon-demo-cura.herokuapp.com/");
        System.out.println("Title: " + driver.getTitle());
        driver.quit();
    }
}
