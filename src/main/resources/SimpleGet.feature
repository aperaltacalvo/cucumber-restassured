@simple
Feature: Simple get request
  Scenario: Send a simple get request to google
    Given A simple GET request to api https://google.es
    When Check that response code is 200
