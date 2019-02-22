Feature: Getting SpaceX Rocket information from the SpaceX-API

  Scenario: Getting information for all current rockets from the SpaceX-API
    Given the https://api.spacexdata.com/v3/rockets is mocked to return some rockets
    When I make a GET request to the "/rockets" endpoint
    Then I receive a response of 4 rockets