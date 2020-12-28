Feature: Login Functionality 
@smoke
Scenario:	Login with valid credentials
When login with valid credentials
Then verify the dashboard logo is displayed

@smoke
Scenario: Login with invalid credentials
When login with invalid credentials
Then verify the error message 


