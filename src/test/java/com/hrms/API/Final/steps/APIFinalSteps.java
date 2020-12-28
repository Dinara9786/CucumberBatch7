package com.hrms.API.Final.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;

import com.hrms.utils.APICommonMethods;
import com.hrms.utils.APIConstants;
import com.hrms.utils.CommonMethods;

public class APIFinalSteps extends CommonMethods {

	RequestSpecification request;
	Response response;
	static String employeeID;

	@Given("a request is prepared to create an employee")
	public void a_request_is_prepared_to_create_an_employee() {
		/**
		 * Using createEmployeeRequest() common method which creates a request by
		 * passing expected parameters
		 */
//		request = apiMethods.createEmployeeRequest(GenerateTokenSteps.token,
//				CommonMethods.readJson(APIConstants.CREATE_EMPLOYEE_JSON));

		request = APICommonMethods.createEmployeeRequest(GenerateTokenSteps.token,
				CommonMethods.readJson(APIConstants.CREATE_EMPLOYEE_JSON));

		/**
		 * The payload MUST be a String! Below line of code is doing the same as above
		 */
		// request = apiMethods.createEmployeeRequest(GenerateTokenSteps.token,
		// APIPayloadCommonMethods.createEmployeeBody());
	}

	@When("a POST call is made to create an employee")
	public void a_POST_call_is_made_to_create_an_employee() {
		response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
	}

	@Then("the status code for creating an employee is {int}")
	public void the_status_code_for_creating_an_employee_is(int statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("the employee is created contains key {string} and value {string}")
	public void the_employee_is_created_contains_key_and_value(String key, String value) {
		response.then().assertThat().body(key, equalTo(value));
	}

	@Then("the employee ID {string} is stored as a global variable to be used for other calls")
	public void the_employee_ID_is_stored_as_a_global_variable_to_be_used_for_other_calls(String value) {
		employeeID = response.jsonPath().getString(value);
	}

	@Given("a request is prepared to retrieve the created employee")
	public void a_request_is_prepared_to_retrieve_the_created_employee() {
		request = APICommonMethods.getOneEmployeeRequest(GenerateTokenSteps.token, employeeID);
	}

	@When("a GET call is made to retrieve the created employee")
	public void a_GET_call_is_made_to_retrieve_the_created_employee() {
		response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
	}

	@Then("the status code for retrieving the created employee is {int}")
	public void the_status_code_for_retrieving_the_created_employee_is(int statusCodeForRetrievingEmployee) {
		response.then().assertThat().statusCode(statusCodeForRetrievingEmployee);
	}

	@Then("the retrieved employee ID {string} matches the globally stored employee ID")
	public void the_retrieved_employee_ID_matches_the_globally_stored_employee_ID(String retrievedEmployeeID) {
		response.then().assertThat().body(retrievedEmployeeID, equalTo(employeeID));
	}

	@Then("the retrieved data at {string} matches the data used to create an employee with employee ID {string}")
	public void the_retrieved_data_at_matches_the_data_used_to_create_an_employee_with_employee_ID(
			String employeeObject, String responseEmployeeID, DataTable dataTable) {

		/**
		 * We are validating expected data that we stored in feature file against actual
		 * data that we are retrieving from the response We are retrieving the response
		 * body by calling the object in feature file
		 */
		/** Retrieving expected data from feature file */
		List<Map<String, String>> expectedData = dataTable.asMaps(String.class, String.class);
		/** Retrieving actual data from response object */
		List<Map<String, String>> actualData = response.body().jsonPath().get(employeeObject);
		/** Starting at index 0 so we can iterate by one after each run */
		int index = 0;
		/** Enhanced loop to iterate through expectedData and store in variable 'map' */
		for (Map<String, String> map : expectedData) {
			Set<String> keys = map.keySet();
			for (String key : keys) {
				String expectedValue = map.get(key);
				String actualValue = actualData.get(index).get(key);
				Assert.assertEquals(expectedValue, actualValue);
			}
			index++;
		}
		String empID = response.body().jsonPath().getString(responseEmployeeID);
		Assert.assertTrue(empID.contentEquals(employeeID));
	}

	@Given("a request is prepared to retrieve all employees")
	public void a_request_is_prepared_to_retrieve_all_employees() {
	}

	@When("a GET call is made to retrieve all employees")
	public void a_GET_call_is_made_to_retrieve_all_employees() {
	}

	@Then("the status code for retrieving all employees is {int}")
	public void the_status_code_for_retrieving_all_employees_is(Integer int1) {
	}

	@Then("the retrieved data contains the globally stored employee ID")
	public void the_retrieved_data_contains_the_globally_stored_employee_ID() {
	}

	@Then("the retrieved data matches the data that was used to create an employee")
	public void the_retrieved_data_matches_the_data_that_was_used_to_create_an_employee() {
	}

}