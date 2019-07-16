Feature: Register for Pretty Penny

	Background:
		Given I am on the register page
		
	Scenario: Registration works
		Given registration fields are correctly filled
		When I click on submit registration
		Then I am redirected home or given an email taken error