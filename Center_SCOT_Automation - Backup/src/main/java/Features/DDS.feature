@DDS
Feature: Center Applications

  Background:
    Given the chrome driver is initiated

  @Request_New_Cache_for_a_Scot
  Scenario: PSB-779-Centre_STG DDS_Request Full Cache in a Till
    And I launched Trading DDS
    And I navigate to main index page
    When I enter Scot number and Store number under Request new Cache a Scot in Cache Service manager
    And I click on New Cache for Scot button
    Then I capture the prompt
    And store number is populated in OrgUnitsBeingBuilt under CacheBuilderManager

  @Cache_Service_manager_Layout
    Scenario: PSB-716-Centre_Trading DDS_Validate the Cache Service Manager layout
    Given I launched Trading DDS
    And I navigate to main index page
    When I validate the entire page layout
    Then layout is as per latest design

  @RequestNewCache_AllTheScots
  Scenario:  TC0048_Centre_Trading DDS_Validte the Internal Message when RequestNewCache for all Tills in a store is clicked in the Cache service manager
    And I launched Trading DDS
    And I navigate to main index page
    When I enter Store number under Request new Cache a Scot in Cache Service manager
    Then I capture the prompt
    And store number is populated in OrgUnitsBeingBuilt under CacheBuilderManager


  @Request_Cache_for_a_Scot
  Scenario: PSB-779-Centre_STG DDS_Request Full Cache in a Till
    And I launched Trading DDS
    And I navigate to main index page
    When I enter Scot number and Store number under Request Cache a Scot in Cache Service manager
    And I click on Cache for Scot button
    Then I capture the prompt for cache

  @Request_Cache_for_a_Store
  Scenario: PSB-779-Centre_STG DDS_Request Full Cache in a Store
    And I launched Trading DDS
    And I navigate to main index page
    When I enter Store number under Request Cache a Scot in Cache Service manager
    And I click on Cache for Store button
    Then I capture the prompt for cache for Store
