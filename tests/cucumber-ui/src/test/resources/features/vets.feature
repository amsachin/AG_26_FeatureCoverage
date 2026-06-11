Feature: Vets page

  @teamPetClinic @appPetClinic @featureVets @funcVetDirectory @subFuncViewVetsPage
  Scenario: View the vets page
    Given the PetClinic application is running
    When I open the vets page
    Then the vets page should be displayed
    And the vets table should include "James Carter"
    And the vets table should include "Helen Leary"
