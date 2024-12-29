Feature: Login functionality
  As a user
  I want to be able to login
  So that I can access the application

  Scenario Outline: Successful login with valid credentials
    Given I open the login page
    When I enter "<username>" and "<password>"
    And I click the login button
    Then I should be redirected to the inventory page

    Examples:
      | username        | password      |
      | standard_user   | secret_sauce  |

  Scenario: Unsuccessful login with invalid credentials
    Given I open the login page
    When I enter "invalid_user" and "invalid_password"
    And I click the login button
    Then I should see an error message

  Scenario: Attempt to login with empty username and password
    Given I open the login page
    When I enter "" and ""
    And I click the login button
    Then I should see an error message for missing credentials

  Scenario: Attempt to login with a username that exceeds the maximum length
    Given I open the login page
    When I enter "this_is_a_very_long_username_exceeding_limits" and "secret_sauce"
    And I click the login button
    Then I should see an error message for invalid username
