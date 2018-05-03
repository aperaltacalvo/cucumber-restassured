@simple
Feature: Simple get request
  Scenario: Send a simple get request to google
    Given A simple GET request to api https://google.es
    When Check that response code is 200
  Scenario: Send a simple delete request to google
    Given A DELETE request to api https://jsonplaceholder.typicode.com/posts/1
    When Check that response code is 200
  Scenario: Send a post with body
    Given I send a POST with body file bodies/post.json to URL https://jsonplaceholder.typicode.com/posts
    Then Check that response code is 201
    And Check that response body is responses/post.json
 Scenario: Send a put
   Given I send a PUT with body file bodies/put.json to URL https://jsonplaceholder.typicode.com/posts/1
   Then Check that response code is 200
   And Check that response body is responses/put.json

