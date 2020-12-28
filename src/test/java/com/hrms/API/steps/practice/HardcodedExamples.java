package com.hrms.API.steps.practice;

import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class HardcodedExamples {

	/*
	 * REST Assured - Java library specifically developed to automate REST endpoints
	 * 
	 * Given - Preparing your request // this is pre-condition When - What action
	 * will you perform, what type of call are you making? Then - Verification
	 * 
	 */

	// *Concatenate

	// we made baseURI and token GLOBAL
	/** Concatenates with endpoint during ruen time */
	String UIR = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api"; // I made them as global
	String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDQ1NDg5NTgsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYwNDU5MjE1OCwidXNlcklkIjoiMjE5NiJ9.e0tDzQ-Kh7zS6h2bqhP3EOU2kfseRvz2ZF3NBzSueKI";
	static String employeeID;
	
	//@Test
	public void sampleTest() {

//		/** Base URI for all endpoints */
//		RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
//
//		/** JWT */
//		String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2MDQyNzc0NzQsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTYwNDMyMDY3NCwidXNlcklkIjoiMjE5NiJ9.0EkJlM9P-Qs0PmUnRpgC7GE6sJYuK8dgNuIHA6IKpbA";

		/** Preparing request for /getOneEmployee.php */
		/** Using .log.all to print out everything being sent with the request */
		RequestSpecification preparingGetOneEmployeeRequest = given().header("Authorization", token)
				.header("Content-type", "application/json").queryParam("employee_id", "3730"); //.log().all();
		
		/** Making call to /getOneEmployee.php */
		Response getOneEmployeeResponse = preparingGetOneEmployeeRequest.when().get("/getOneEmployee.php");
		
		/** One way to print response object */
		//System.out.println(getOneEmployeeResponse.asString());
		
		/** Second way using rest assured prettyPrint();*/
		//getOneEmployeeResponse.prettyPrint();
		
		/** Using assertThat() to verify status code */
		getOneEmployeeResponse.then().assertThat().statusCode(200);

	}

	@Test
	public void aPOSTcreateEmployee () {
		
	/** Preparing request for creating an Employee */	
	RequestSpecification createEmployeeRequest = given().header("Authorization", token).header("Content-Type", "application/json").body("{\r\n" + 
				"  \"emp_firstname\": \"Jane\",\r\n" + 
				"  \"emp_lastname\": \"Lucky\",\r\n" + 
				"  \"emp_middle_name\": \"Batch\",\r\n" + 
				"  \"emp_gender\": \"F\",\r\n" + 
				"  \"emp_birthday\": \"1999-07-27\",\r\n" + 
				"  \"emp_status\": \"Freelance\",\r\n" + 
				"  \"emp_job_title\": \"Network Administrator\"\r\n" + 
				"}");//.log().all();
		
	/** making call to/createEmployeephp */
	Response createEmployeeResponse =  createEmployeeRequest.when().post("/createEmployee.php");
	
	/** Printing response */
	//createEmployeeResponse.prettyPrint();
	
	/** jsonPath() to view the response body which lets us get the employee ID
	 * We are storing the employee ID as a static global variable to be able to use 
	 * with other calls
	 * */
	employeeID = createEmployeeResponse.jsonPath().getString("Employee[0].employee_id");
	
	/** Optional: Printing employeeID */
	//System.out.println(employeeID);
	
	createEmployeeResponse.then().assertThat().statusCode(201);
	
	/** Verifying response body "Message" is paired with "Entry Created";
	 * equalTo() method comes from stati Hamcrest package - NEED TO IMPORT MANUALLY 
	 * import static org.hamcrest.Matchers.*; */
	createEmployeeResponse.then().assertThat().body("Message", equalTo("Entry Created"));
	
	
	/** Verifying created employee first name */
	createEmployeeResponse.then().assertThat().body("Employee[0].emp_firstname", equalTo("Jane"));
	
	/** Verifying server Apache/2.4.39 (Win64) PHP/7.2.18 */
	createEmployeeResponse.then().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");
	
	}
	
	@Test
	public void bGetCreatedEmployee() {
		
		/** Preparing request to get created employee */
		RequestSpecification getCreatedEmployeeRequest = given().header("Content-Type", "application/json").header("Authorization", token)
				.queryParam("employee_id", employeeID);
		
		/** Storing response for retrieving created employee */
		Response getCreatedEmployeeResponse = getCreatedEmployeeRequest.when().get("/getOneEmployee.php");
		
		/** Printing response */
		//getCreatedEmployeeResponse.prettyPrint();
		
		/**  Storing response employee ID into empID to compare with static global employee ID */
		String empID = getCreatedEmployeeResponse.body().jsonPath().getString("employee[0].employee_id");
		
		/** Comparing empID with stored employeeID from created employee call */
		boolean verifyingEmployeeID = empID.equalsIgnoreCase(employeeID);
		
		/** Asserting to verify the above condition is true */
		Assert.assertTrue(verifyingEmployeeID);
		
		/** Verifying status code is 200 */
		getCreatedEmployeeResponse.then().assertThat().statusCode(200);
		
		/** Storing full response as a string so that we are able to pass it a s an argument with */
		String response = getCreatedEmployeeResponse.asString();
		
		/** Created object of JsonPath */
		JsonPath js = new JsonPath(response); // we created object of the class
		
		/** Grabbing employee ID using 'js' */
		String employeeId = js.getString("employee[0].employee_id");
		String firstName = js.getString("employee[0].emp_firstname");
		String middleName = js.getString("employee[0].emp_middle_name");
		String lastName = js.getString("employee[0].emp_lastname");
		String birthday = js.getString("employee[0].emp_birthday");
		String gender = js.getString("employee[0].emp_gender");
		String jobTitle = js.getString("employee[0].emp_job_title");
		String status = js.getString("employee[0].emp_status");
		
		/** Asserting response employee ID matches stored employee ID */
		Assert.assertTrue(employeeId.contentEquals(employeeID));
		
		/** Asserting the rest of the values match expected data */
		Assert.assertEquals(firstName, "Jane");
		Assert.assertEquals(middleName, "Batch");
		Assert.assertEquals(lastName, "Lucky");
		Assert.assertEquals(birthday, "1999-07-27");
		Assert.assertEquals(gender, "Female");
		Assert.assertEquals(jobTitle, "Network Administrator");
		Assert.assertEquals(status, "Freelance");
		
		
	}

	@Test
	public void cGETallEmpoloyee() {
		
		/** Preparing request to get all employees */
		RequestSpecification getAllEmployeesRequest = given().header("Content-Type", "application/json").header("Authorization", token);
		
		/** Storing response into getAllEmployeesResponse */
		Response  getAllEmployeesResponse = getAllEmployeesRequest.when().get("/getAllEmployees");
		
		/** Printing response */
		//getAllEmployeesResponse.prettyPrint();
		
		/** Storing response as a String */
		String response = getAllEmployeesResponse.asString();
		
		/** Creating object of JsonPath and passing response as a String as an argument */
		JsonPath js = new JsonPath(response);
		
		/** Retrieving the size of the array 9the numbe ro object in the array)*/
		int count = js.getInt("Employees.size()");
		//System.out.println(count);
		
//		for (int i=0; i<count; i++) {
//			
//			String allEmployeeIDs = js.get("Employees[" + i + "].employee_id");
			
			//System.out.println(allEmployeeIDs);
			
//			if (allEmployeeIDs.contentEquals(employeeID)) {
//				System.out.println("Employee ID: " + employeeID + " is present in the body");
//				
//				String firstNameOfEmpID = js.getString("Employees[" + i + "].emp_firstname");
//				System.out.println(firstNameOfEmpID);
//				break;
//				
//			}
//		}
//		
//		/** for loop to print out first names of all employees */
//		
//		for (int i=0; i < count; i++) {
//			
//			String allFirstNames = js.getString("Employees[" + i + "].emp_firstname");
//			System.out.println(allFirstNames);
//			
		}
	
	@Test
	public void dPUTupdateCreatedEmployee() {
		/** Task completed */
		given().body("{\r\n" + 
				"  \"employee_id\": \"" + employeeID + "\" ,\r\n" + 
				"  \"emp_firstname\": \"Gloria\",\r\n" + 
				"  \"emp_lastname\": \"Alex\",\r\n" + 
				"  \"emp_middle_name\": \"Martin\",\r\n" + 
				"  \"emp_gender\": \"F\",\r\n" + 
				"  \"emp_birthday\": \"1995-06-29\",\r\n" + 
				"  \"emp_status\": \"Super Contractor\",\r\n" + 
				"  \"emp_job_title\": \"Cloud Architect\"\r\n" + 
				"}");
		
		
	}
		
	}
	

