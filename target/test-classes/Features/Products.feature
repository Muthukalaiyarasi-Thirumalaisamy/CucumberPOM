Feature: Products Page

Background:
Given user has already logged into application
|username|password|
|standard_user|secret_sauce|

Scenario: Products page Title
Given user is on Products page
When user gets the title of the page
Then page title should be "Swag Labs"

Scenario: Login with valid credentials
Given user is on Products page
Then user get prodducts list
|Sauce Labs Backpack|
|Sauce Labs Bike Light|
|Sauce Labs Bolt T-Shirt|
|Sauce Labs Fleece Jacket|
|Sauce Labs Onesie|
|Test.allTheThings() T-Shirt (Red)|
And products count should be 6
