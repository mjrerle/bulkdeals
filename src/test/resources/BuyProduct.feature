Feature: Buy a product

  Scenario: Filter Sumsung brand product
    Given I am on products page
    When I click samung fliter by brand name
    Then I shoud see only Samsung produts

  Scenario: Add to card Sumsung brand product
    When I click add to card on product
    Then Product should be on cart

  Scenario: Chekout the Cart
    Given I am on cart page
    When I click checkout button
    Then Cart shoud be empty
