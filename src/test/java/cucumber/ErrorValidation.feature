@tag
Feature: Error Validation
 

  @ErrorValidation
  Scenario Outline: Validating negative scenarios
    Given I landed on Ecommerce Page
    When I logged in with userName <userName> and password <password>
    Then "Incorrect email or password." message is displayed

    Examples: 
      | userName           |    password           |
      | swapna@gamil.com   |    Swapna@123$gngng   |

