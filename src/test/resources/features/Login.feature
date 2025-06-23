@Cura
Feature: Login Functionality

  Background:
    Given the user is logged out
    When the user clicks the "Login" button from navigation bar

    # ---------- HAPPY PATH ---------- #
  Scenario: Login with valid credentials redirects to appointment page
    When the user logs in with "valid" username "valid.username" and password "valid.password"
    Then the "Make Appointment" page should be displayed

    # ---------- NEGATIVE PATH ---------- #
  Scenario Outline: Logging in with invalid credentials
    When the user logs in with "invalid" username "<username>" and password "<password>"
    Then an error message "Login failed! Please ensure the username and password are valid." should be displayed

    Examples:
      | username  | password       |
      | wronguser | ThisIsNotAPass |
      | John Doe  | wrongpass      |