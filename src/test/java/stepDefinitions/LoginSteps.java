package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BasePageObjects;
import pageObjects.LoginPageObjects;

public class LoginSteps extends BasePageObjects {

    private LoginPageObjects loginPageObjects;

    public LoginSteps(LoginPageObjects loginPageObjects) {
        this.loginPageObjects = loginPageObjects;
    }

    @Then("I should be presented with the successful login message")
    public void i_should_be_presented_with_the_successful_login_message() {
        loginPageObjects.validateSuccessfulLoginMessage();
    }

    @Then("I should be presented with the unsuccessful login message")
    public void i_should_be_presented_with_the_unsuccessful_login_message() {
        loginPageObjects.validateUnsuccessfulLoginMessage();
    }

    @Then("I should be presented with the following login validation message {}")
    public void i_should_be_presented_with_the_following_login_validation_message(String expectedMessage) {
        waitForAlertAndValidateText(expectedMessage);
    }

    @Given("User has accessed login page")
    public void userHasAccessedLoginPage() {
        loginPageObjects.navigateToKrisdemoLoginPage();
    }

    @When("User enters credentials {string} and {string}")
    public void userEntersCredentialsAnd(String username, String password) {
        loginPageObjects.setUser(username);
        loginPageObjects.setPass(password);
        loginPageObjects.clickLoginBtn();
    }
}