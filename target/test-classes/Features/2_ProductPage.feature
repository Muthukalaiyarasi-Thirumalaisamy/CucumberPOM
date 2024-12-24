Feature: Products Page

Background:
Given user has already logged into application
|username|password|
|standard_user|secret_sauce|
And user is on Products page
And user gets products list
    |Sauce Labs Backpack            |
    |Sauce Labs Bike Light          |
    |Sauce Labs Bolt T-Shirt        |
    |Sauce Labs Fleece Jacket       |
    |Sauce Labs Onesie              |
    |Test.allTheThings() T-Shirt (Red)|


Scenario: Products page Title
When user gets the title of the page
Then page title should be "Swag Labs"

Scenario: Verify products list and count
  Then products count should be 6

Scenario: Add product to cart if it matches actual products
  When user searches for "Sauce Labs Fleece Jacket"
  Then if product name "Sauce Labs Fleece Jacket" matches actual products, it should be added to the cart
  And cart count should increase by 1

 
