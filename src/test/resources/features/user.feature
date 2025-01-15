Feature: Create a new user in the system

  Scenario Outline: Add a new user with valid details
    Given I have the user details "<userID>", "<userName>", "<FName>", "<LName>", "<email>", "<Pwd>", and "<Ph>"
    When I send a POST request to create the user
    Then I should receive a 200 status code in the response
    And the user should be successfully created

    Examples: 
      | userID | userName  | FName | LName | email                | Pwd     | Ph         |
      |1001 	 | testuser1 | John  | Doe   | john.doe@example.com | pass123 | 1234567890 |

  