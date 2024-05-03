Feature: Customers 


Background: Steps common for all tc
Given User launch Google Chrome
When User opens the url "https://admin-demo.nopcommerce.com/login?returnUrl=%2F"
And user enters email as "admin@yourstore.com" & password as "admin"
And click on login button
Then Page title should be "Dashboard / nopCommerce administration"
When user clicks on Customers menu
And user clicks on Customers menu item

@Sanity
Scenario: Add a New Customer
And user clicks on add new button
Then user can view add new customer page
When user enters details of new customer
And click on save button
Then User can view the confirmation message "The new customer has been added successfully."
And user closes the browser

@Regression
Scenario: Search Customer by Email
And enter customer email
When Click on search button
Then User should found Email in the Search table
And user closes the browser 
	
@Regression
Scenario: Search Customer by Name
And Enter customer FirstName
And Enter customer LastName
When Click on search button
Then User should found Name in the Search table
And user closes the browser