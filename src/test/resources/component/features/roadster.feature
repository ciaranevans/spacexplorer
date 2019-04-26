Feature: Getting SpaceX Roadster information from the SpaceX-API

  Scenario: Getting information for Roadsters current location and various other data
    Given "/v3/roadster" is mocked to return roadsters data
    When I make a GET request to the "/roadster" endpoint
    Then I receive a response of correct roadster data