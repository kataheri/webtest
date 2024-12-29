package stepdefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;

import static org.junit.Assert.*;

public class LoginSteps {
    WebDriver driver;
    LoginPage loginPage;

    @Given("I open the login page")
    public void iOpenTheLoginPage() {
        driver = new ChromeDriver();
        driver.get("https://saucedemo.com");
        loginPage = new LoginPage(driver);
    }

    @When("I enter {string} and {string}")
    public void iEnterAnd(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("I should be redirected to the inventory page")
    public void iShouldBeRedirectedToTheInventoryPage() {
        String currentUrl = driver.getCurrentUrl();
        assertTrue("User is not on the inventory page", currentUrl.contains("inventory.html"));
        driver.quit();
    }

    @Then("I should see an error message")
    public void iShouldSeeAnErrorMessage() {
        String errorMessage = loginPage.getErrorMessage();
        assertFalse("Error message is not displayed", errorMessage.isEmpty());
        driver.quit();
    }

    @Then("I should see an error message for missing credentials")
    public void iShouldSeeAnErrorMessageForMissingCredentials() {
        String errorMessage = loginPage.getErrorMessage();
        assertTrue("Error message for missing credentials is not displayed", errorMessage.contains("Username is required"));
        driver.quit();
    }

    @Then("I should see an error message for invalid username")
    public void iShouldSeeAnErrorMessageForInvalidUsername() {
        String errorMessage = loginPage.getErrorMessage();
        assertTrue("Error message for invalid username is not displayed", errorMessage.contains("Username and password do not match"));
        driver.quit();
    }
}
