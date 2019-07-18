
Feature: Add a product to sell

	Background:
		Given I am logged in as a seller and on the add product page

  Scenario: Add product normal price
    Given I have entered the necessary values
    When I submit the product at standard sale
    Then I receive a product added notification

