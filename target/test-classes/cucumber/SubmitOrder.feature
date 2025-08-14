@tag
Feature: Purchase the order from Ecommerce Website
  
  Background: 
  Given I landed on Ecommerce Page
  
  @Regression
  Scenario Outline: Positive Test of Submitting the Order
    Given I logged in with userName <userName> and password <password>
    When I add product <product> to the Cart
    And Checkout product <product> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on the ConfirmationPage

    Examples: 
      |      userName      |       password   | product       |
      | swapna@gamil.com   |    Swapna@123$   | IPHONE 13 PRO |
      | swapna@gamil.com   |    Swapna@123$   | ZARA COAT 3   |
     
