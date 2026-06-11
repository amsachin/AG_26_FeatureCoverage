Feature: Owners pages

  @teamPetClinic @appPetClinic @featureOwners @funcOwnerManagement @subFuncListOwners
  Scenario: View the owners list page
    Given the PetClinic application is running
    When I open the owners page
    Then the owners page should be displayed
    And the owners table should include "George Franklin"
    And the owners table should include "Betty Davis"

  @teamPetClinic @appPetClinic @featureOwners @funcOwnerManagement @subFuncReadOwner @bug @bugHYP-123
  Scenario: View an owner details page
    Given the PetClinic application is running
    When I open the owner details page for owner 1
    Then the owner details page should be displayed
    And the owner details should include "George Franklin"
    And the owner details should include "Leo"

  @teamPetClinic @appPetClinic @featureOwners @funcOwnerManagement @subFuncFindOwners
  Scenario: View the find owners page
    Given the PetClinic application is running
    When I open the find owners page
    Then the find owners page should be displayed
    And the find owners form should be displayed
