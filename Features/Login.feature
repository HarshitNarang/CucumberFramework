Feature: Login 

@Sanity @Regression
Scenario: Successful Login with valid Credentials
Given User launch Google Chrome
When User opens the url "https://admin-demo.nopcommerce.com/login?returnUrl=%2F"
And user enters email as "admin@yourstore.com" & password as "admin"
And click on login button
Then Page title should be "Dashboard / nopCommerce administration"
When user clicks logout button
Then Page title should be "Your store. Login"
And user closes the browser


@Regression
Scenario Outline: Login with valid invalid Credentials using DataDriven
Given User launch Google Chrome
When User opens the url "https://admin-demo.nopcommerce.com/login?returnUrl=%2F"
And user enters email as "<email>" & password as "<password>"
And click on login button
Then Page title should be "Dashboard / nopCommerce administration"
When user clicks logout button
Then Page title should be "Your store. Login"
And user closes the browser

Examples:
|email|password|
|admin@yourstore.com|admin|
|adminwrong@gmail.com|admin|

