@login

Feature: WebDriver University - Login Page

  Scenario: Validate Successful Login
    Given I access the webdriver university login page
    When I enter a username "webdriver"
    And I enter a password webdriver123
    And I click on the login button
    Then I should be presented with the successful login message
