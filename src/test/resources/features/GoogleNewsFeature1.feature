Feature: Navigate to Google News and click on the second last link

  Scenario: Load Google News and click on second last link
    Given User navigate to Google News
    When User scroll to the bottom and click the second last link
    And User close the browser