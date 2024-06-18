@OWBCheck
Feature: OWB functionality

Background:
  Given the chrome driver is initiated
  And I launch the OWB
  And I login to OWB

  @OWBReboot
  Scenario: PSB-193,PSB-79
    When I keyin the StoreID
    And I keyin the TillID
    And I click on search button
    Then the till status page appears
    When I click on reboot
    Then confirmation prompt appears
    When I click on yes button
    Then the request is sent successfully
    When I check on the events
    Then the shutdown event is audited


  @TillDetails
  Scenario: PSB-762

    When I click on Estate status link
    Then estate status page appears
    When I click on StoreID
    Then Store status page appears
    When I click on TillID
    Then the till status page appears
    When I click on estate status option
    Then it navigates to home page

  @DeltaDeliveryScreen
  Scenario: PSB-407,PSB-353
    Given the chrome driver is initiated
    And I launch the OWB
    And I login to OWB
    When I click on Estate status link
    Then estate status page appears
    When I click on data distribution page
    Then batch processing page appears
    When I select the date
    Then all the batch details are listed with current status
    When I click on estate status option
    Then it navigates to home page

  @RequestCache
  Scenario: PSB-664
    When I keyin the StoreID
    And I keyin the TillID
    And I click on search button
    Then the till status page appears
    When I click on cache option
    And I click on request cache
    Then confirmation prompt appears
    When I click on yes button
    Then response message is shown
    When I click on estate status option
    Then it navigates to home page

  @StoreSummaryLayout
  Scenario: PSB-34
    When I click on Estate status link
    Then estate status page appears
    And I check for status layout


  @StoreSummaryField
  Scenario: PSB-129
    Given the chrome driver is initiated
    And I launch the OWB
    And I login to OWB
    When I keyin the StoreID
    And I click on store search button
    When the summary page appears
    Then I check for the layout



  @TillDetailsLayout
  Scenario: PSB-846

    When I keyin the StoreID
    And I keyin the TillID
    And I click on search button
    Then the till status page appears
    And all the parameters listed in the page