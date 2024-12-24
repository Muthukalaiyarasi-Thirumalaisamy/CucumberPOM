Feature: Cart Page
Background:
Given user has already logged into application
|username|password|
|standard_user|secret_sauce|

Scenario: Check title of the Cart page 
When user is added product below to cart
|Sauce Labs Fleece Jacket| 
|Sauce Labs Backpack|
Then user is on Cart page
And user should get the title of cart page
Then title of the cart page should be "Your Cart"

Scenario: Check the product added on Cart is expected Product
When product added should be same as expected products 
|Sauce Labs Fleece Jacket| 
|Sauce Labs Backpack|
Then user should get the count of the product added in cart as 2
