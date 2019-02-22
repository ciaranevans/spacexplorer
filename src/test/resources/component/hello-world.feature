Feature: Basic standing up and poking SpaceXplorer

  Scenario: Hitting hello world
    When I make a GET request to the "/hello" endpoint
    Then I receive a response of
    """
    Hello, World!
    """