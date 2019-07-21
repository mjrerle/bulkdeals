Feature: Fail to enter all login fields
  
  
  Background:
    Given I am on the login page again
  
  Scenario: No password alert works
    Given email is entered
    When I click the login button again
    Then I get an alert

