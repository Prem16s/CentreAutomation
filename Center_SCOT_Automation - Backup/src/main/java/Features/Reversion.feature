@Reversion
Feature: Center Applications

  Background:
    Given the chrome driver is initiated

  @Change_User_Role_Reversion
  Scenario: Centre_BO_User Maintenance Add User
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose user option  from the maintanence tab
    And I enter the user name for whom Role being changed
    And I remove the manager user Role
    And I save user role the changes
    Then Batch Reference number is captured

    @Reversion_Beanstore_trading_hours_for_a_Store
    Scenario: Reverting the Start and End time for a Store
         And I logged in Trading BackOffice
         And I entered the required store
         And I choose Local Store in the Maintenance tab
         When I reverted the trading hour details by daywise I save the changes
         Then  Batch Reference number is captured

  @Reversion_Licensing_hours_for_a_Store
  Scenario: Reverting Licensing hours for a Store
      And I logged in Trading BackOffice
      And I entered the required store
      And I choose Time Restricted in the Maintenance tab
      When I reverted the details for the Licensing hours
      Then  Batch Reference number is captured

      @Reversion_Removing_Optional_Receipt_Prompt_for_a_Store
        Scenario: Removing Optional Receipt Prompt for a Store
          And I logged in Trading BackOffice
          And I choose HeadOffice
          And I choose Print Receipt Restriction option from maintanence menu
          And I choose Remove Receipt Restriction Value against SCOT
          When I added the store details for Optional Receipt Prompt
          Then  Batch Reference number is captured

      @Reversion_Removing_E_Receipt_Prompt_for_a_Store
      Scenario: Removing E-Receipt Prompt for a Store
          And I logged in Trading BackOffice
          And I choose HeadOffice
          And I choose Print Receipt Restriction option from maintanence menu
          And I disabled E Receipt against SCOT
          When I added the store details for E Receipt Prompt
          Then  Batch Reference number is captured

  @Reversion_removal_of_user_profile_from_a_specific_store
  Scenario: Reversion of  TC0018 Centre BO removal of user profile from a specific store
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose user option  from the maintanence tab
    When I enter the user name for whom Store being removed
    And I add store for the user
    And  I save user role the changes
    Then Batch Reference number is captured

    @Reversion_Product_Name_and_Receipt_Description
    Scenario: Reverting Product Name and Receipt Description
      And I logged in Trading BackOffice
      And I choose HeadOffice
      And I choose product option  from the maintanence tab
      And I search for a product
      When I revert the product name and Receipt description
      And I save the change
      Then  Batch Reference number is captured

  @Reversion_ReasonCode_Maintenance
  Scenario: TC0031_Centre_BO_Apply Reason Code Maintenance
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Reason code option from the maintanence tab
    And I select for a Reason code
    When I disable a Reason code
    And I added stores
    And I save the change
    And I select activate button
    Then  Batch Reference number is captured

    @Reversion_Store_Maintenance_Store_Level
    Scenario: TC0029_Centre_BO_Store Maintenance Store Level
      And I logged in Trading BackOffice
      And I choose HeadOffice
      And I choose Store option from the maintanence tab
      And I select a store
      When I revert the auto secure time
      Then  Batch Reference number is captured

  @Reversion_Store_Maintenance_Group_Level
  Scenario: TC0030_Centre_BO_Store Maintenance Group Level
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Store maintanence by group option from the maintanence tab
    When I remove receipt footer
    And I added stores
    And I save the store Maintenance changes
    Then  Batch Reference number is captured

    @Reversion_Update_Currency_Exchange_Rates
    Scenario:  Reversion of TC0026_Centre_BO_Update Currency Exchange Rates
      And I logged in Trading BackOffice
      And I choose HeadOffice
      And I choose Currency Exchange rate option from the maintanence tab
      When I choose Pound as Base Currency
      And I Revert the Pound currency value against Australian Dollar
      And I save the currency changes
      Then  Batch Reference number is captured

  @Reversion_Functions_By_Personality_CompanyLevel
  Scenario:  TC0022_Functions_By_Personality_CompanyLevel
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Function by personality company option from the maintanence tab
    And I choose personality
    And I enable product selection option
    When i save Function by personality changes
    Then  Batch Reference number is captured

  @Reversion_Functions_By_Personality_StoreGroups
  Scenario: Reversion TC0022_Functions_By_Personality_StoreGroups
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Function by personality StoreGroups option from the maintanence tab
    And I choose personality
    And I enable Customer Reservation option
    And I added stores
    When i save Function by personality Store group changes
    Then  Batch Reference number is captured

  @Reversion_Receipt_Suppression
  Scenario: Reversion TC0036_Centre_BO_Setting of Receipt suppression for store
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Receipt restriction option from the maintanence tab
    When I remove suppression for  cafe
    And I added stores
    And I save the changes
    Then  Batch Reference number is captured

  @Reversion_Maintain_table_layout
  Scenario: Reversion TC0053_Centre_BO_Maintain table layout functionality
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Maintain table layout option from the maintanence tab
    And I add stores to change the table layout
    When I set Medium Deli layout for Deli
    Then  Batch Reference number is captured

  @Reversion_Privilege_Maintenance
  Scenario: TC0037_Centre_BO_Privilege_Maintenance
    And I logged in Trading BackOffice
    And I choose HeadOffice
    And I choose Privilege Maintenance option
    When I add item refund Privilege for advisor
    And I save the Privilege changes
    Then  Batch Reference number is captured

  @Reversion_of_Red_Alert_Message
  Scenario: Removing an item with Red Alert from a Store
      And I logged in Trading BackOffice
      And I choose HeadOffice
      And I choose Message option from maintanence menu
      And I opened the Red Alert with UPC
      When I removed the store for reversion I save the changes
      Then  Batch Reference number is captured

  @Reversion_of_Bulk_RTM_Product
  Scenario: Removing a Bulk RTM Restriction for an item from a Store
       And I logged in Trading BackOffice
       And I choose HeadOffice
       And I choose Message option from maintanence menu
       And I opened the Bulk RTM with UPC
       When I removed the store for reversion I save the changes
       Then  Batch Reference number is captured

  @Reversion_of_Product_Restricted_item
  Scenario: Removing Product Restriction for an item from a Store
       And I logged in Trading BackOffice
       And I choose HeadOffice
       And I choose Message option from maintanence menu
       And I opened the Product Restricted item with UPC
       When I removed the store for reversion I save the changes
       Then  Batch Reference number is captured

  @Reversion_of_CSAT_Message
   Scenario: Removing CSAT Message from a Store
         And I logged in Trading BackOffice
         And I choose HeadOffice
         And I choose Message option from maintanence menu
         And I opened the created CSAT Message
         When I removed the store for reversion I save the changes
         Then  Batch Reference number is captured

   @Reversion_of_Sales_Message_item
   Scenario: Removing Sales Message for an item from a Store
         And I logged in Trading BackOffice
         And I choose HeadOffice
         And I choose Message option from maintanence menu
         And I opened the Sales Message item with UPC
         When I removed the store for reversion I save the changes
         Then  Batch Reference number is captured

   @Reversion_of_Quantity_Restricted_item
   Scenario: Removing Quantity Restricted for an item from a Store
          And I logged in Trading BackOffice
          And I choose HeadOffice
          And I choose Message option from maintanence menu
          And I opened the Quantity Restricted item with UPC
          When I removed the store for reversion I save the changes
          Then  Batch Reference number is captured
