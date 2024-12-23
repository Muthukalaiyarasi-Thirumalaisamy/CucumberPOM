Feature: Login Page

Scenario: Login page Title
Given user is on login page
When user gets the title of the page
Then page title should be "Swag Labs"

Scenario: Login with valid credentials
Given user is on login page
When user enters username "standard_user"
And user enters password "secret_sauce"
And user clicks on login button
Then user gets the title of the page
And page title should be "Swag Labs"