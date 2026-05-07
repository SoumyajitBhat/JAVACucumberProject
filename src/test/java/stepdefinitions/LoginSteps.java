package stepdefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.en.*;
import utils.DriverManager;

public class LoginSteps {
    WebDriver driver = DriverManager.getDriver();
    @Given("user is on login page")
    public void user_is_on_login_page() {
        System.out.println("User is on login page");
        System.out.println(driver.getTitle());
        Assert.assertEquals("YouTube", driver.getTitle());
    }

    @When("user enters valid username and password")
    public void user_enters_valid_username_and_password() {
        System.out.println("User enters credentials");
    }

    @Then("user should be logged in successfully")
    public void user_logged_in_successfully() {
        System.out.println("Login successful");
    }
}
