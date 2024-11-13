Feature: Retrieve users from JSONPlaceholder API
  As a developer
  I want to retrieve a list of users from the JSONPlaceholder API
  So that I can verify the API's functionality


  Scenario: Retrieve all users
    Given the API endpoint is "/users"
    When I send a GET request to the API
    Then the response status code should be 200
    And the response should contain a list of 10 users


  Scenario Outline: Retrieve user details
    Given the API endpoint is "/users/<id>"
    When I send a GET request to the API
    Then the response status code should be 200
    And the response should contain the user details:
      | field        | value             |
      | id           | <id>              |
      | name         | <name>            |
      | username     | <username>        |
      | email        | <email>           |

    Examples:
      | id | name             | username    | email                  |
      | 1  | Leanne Graham    | Bret        | Sincere@april.biz      |
      | 2  | Ervin Howell     | Antonette   | Shanna@melissa.tv      |
      | 3  | Clementine Bauch | Samantha    | Nathan@yesenia.net     |
      | 4  | Patricia Lebsack | Karianne    | Julianne.OConner@kory.org |
      | 5  | Chelsey Dietrich | Kamren      | Lucio_Hettinger@annie.ca |


  Scenario: Verify user address
    Given the API endpoint is "/users/1"
    When I send a GET request to the API
    Then the response status code should be 200
    And the response should contain the user address:
      | field        | value             |
      | street       | Kulas Light       |
      | suite        | Apt. 556          |
      | city         | Gwenborough       |
      | zipcode      | 92998-3874        |
      | geo.lat      | -37.3159          |
      | geo.lng      | 81.1496           |