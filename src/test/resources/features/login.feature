Feature: Login

  @SCRUM-TC-322
  Scenario: Successful login validation
    Given user is on login page
    When user enters valid username and password
    Then user should be logged in successfully
