Feature: Login to Pretty Penny
  
  
  Background:
    Given I am on the login page
  
  Scenario: Logging in works
    Given email and password are entered
    When I click the login button
    Then I am redirected to home

