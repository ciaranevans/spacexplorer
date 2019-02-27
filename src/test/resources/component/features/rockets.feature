Feature: Getting SpaceX Rocket information from the SpaceX-API

  Scenario: Getting information for all current rockets from the SpaceX-API
    Given "/v3/rockets" is mocked to return some rockets
    When I make a GET request to the "/rockets" endpoint
    Then I receive a response of "2" rockets

  Scenario: Getting information for a given rocket from the SpaceX-API
    Given "/v3/rockets/myRocketId" is mocked to return one rocket
    When I make a GET request to the "/rockets/myRocketId" endpoint
    Then I receive a response of one rocket