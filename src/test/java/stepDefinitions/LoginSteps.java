package stepDefinitions;

import io.cucumber.java.en.And;
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

    @Given("I access the webdriver university login page")
    public void i_access_the_webdriver_university_login_page() {
        loginPageObjects.navigateTo_WebDriverUniversity_Login_Page();
    }

    @When("I enter a username {}")
    public void i_enter_a_username(String username) {
        loginPageObjects.setUsername(username);
    }

    @And("I enter a password {}")
    public void i_enter_a_password(String password) {
        loginPageObjects.setPassword(password);
    }

    @And("I click on the login button")
    public void i_click_on_the_login_button() {
        loginPageObjects.clickOn_Login_Button();
    }

    @Then("I should be presented with the successful login message")
    public void i_should_be_presented_with_the_successful_login_message() {
        loginPageObjects.validate_SuccessfulLogin_Message();
    }

    @Then("I should be presented with the unsuccessful login message")
    public void i_should_be_presented_with_the_unsuccessful_login_message() {
        loginPageObjects.validate_UnsuccessfulLogin_Message();
    }

    @Then("I should be presented with the following login validation message {}")
    public void i_should_be_presented_with_the_following_login_validation_message(String expectedMessage) {
        waitForAlert_And_ValidateText(expectedMessage);
    }
}