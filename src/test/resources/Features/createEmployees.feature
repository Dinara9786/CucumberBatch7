@CreateEmployees
Feature: Creating Employees

Description: This feature creates employees 

Background: a JWT is generated

Scenario Outline: Creating employees

Given a request is prepared to create employees
When employee data provided is "<employeeFirstName>", "<employeeLastName>", "<employeeMiddleName>", "<employeeGender>",
	"<employeeBirthday>", "<employeeJobStatus>", "<employeeJobTitle>"
Then employees are created

Examples:
| employeeFirstName | employeeLastName 	| employeeMiddleName 	| employeeBirthday 	| employeeGender 	| employeeJobStatus 	| employeeJobTitle 			|
| Jane          		| Lucky           	| Batch        				| 1999-07-27   			| Female     			| Freelance 					| Network Administrator | 
| Dinara         		| Rysalieva        	| S		        				| 1970-07-18   			| Female     			| Employee	 					| Automation Engineer		|
| Akerke         		| Tungatarova      	| B		        				| 2005-08-25   			| Female     			| Employee	 					| Software Engineer			|