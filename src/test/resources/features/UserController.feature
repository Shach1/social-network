Feature: User Controller
  Scenario: Get all users
    Given the user service returns a list of users
    When the client calls GET /api/v1/users
    Then the client receives status code 200
    And the client receives a list of users

  Scenario: Create user
    Given the user service can add a user
    When the client calls POST /api/v1/users with user data
    Then the client receives status code 200
    And the client receives the created user