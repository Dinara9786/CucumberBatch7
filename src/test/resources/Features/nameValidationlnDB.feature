Scenario: Feature: Name validations against DB
@dbValidation

Scenario: First name validation against DB - searching by Employee ID
When login with valid credentials
Then verify the dashboard logo is displayed
When navigate to employee list
And enter a valid Employee id "0056266"
Then click on search Button
And verify the table is displayed
When get first name from the table
Then get first name from the db
Then validate first names from ui against db

