@Cura
Feature: Logout Functionality

  # ---------- HAPPY PATH ---------- #
  Scenario: User logs out successfully
    Given the user is logged in
    When the user clicks the "Logout" button from navigation bar
    Then the user should be logged out

  # ---------- NEGATIVE PATH ---------- #
  Scenario: User attempts to access appointment page after logout
    Given the user is logged out
    When the user clicks the "Make Appointment" button
    Then the "Login" page should be displayed
