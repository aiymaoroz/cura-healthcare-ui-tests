Feature: Booking Appointment Functionality

  Background:
    Given the user is logged in
    And the user clicks the "Make Appointment" button

    # ---------- HAPPY PATHS ---------- #
  Scenario Outline: Book appointment with valid details and confirmation
    When the user fills the appointment form with:
      | field       | value         |
      | facility    | <facility>    |
      | readmission | <readmission> |
      | program     | <program>     |
      | date        | <date>        |
      | comment     | <comment>     |
    And the user clicks the "Book Appointment" button
    Then the "Confirmation" page should be displayed
    And the details should match:
      | field    | expected value |
      | facility | <facility>     |
      | program  | <program>      |
      | date     | <date>         |
      | comment  | <comment>      |

    Examples:
      | facility | readmission | program  | date       | comment     |
      | Tokyo    | Yes         | Medicare | 25/06/2025 | Follow-up   |
      | Seoul    | No          | Medicaid | 28/06/2025 | Test case 2 |
      | Hongkong | Yes         | None     | 30/06/2025 |             |

  @Cura
    # ---------- NEGATIVE PATHS ---------- #
  Scenario: Booking an appointment with an empty visit date
    When the user clicks the "Book Appointment" button
    Then the message containing "Please fill out this field" should be displayed on date field
    And the user should stay on the appointment page

  Scenario: Booking an appointment with a past visit date should fail
    When the user fills the date field with "01/01/2020"
    And the user clicks the "Book Appointment" button
    Then the appointment should not be confirmed