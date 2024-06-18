@DDSValidation
Feature: Center Applications

  Background:
    Given the chrome driver is initiated

  @SalesMessage
  Scenario: DDS validation
    And I launched Trading DDS
    And I get deliveryID corresponding to BatchRefNumber
    And I search for a till
    When I get status for the delta
    Then I close the browser
