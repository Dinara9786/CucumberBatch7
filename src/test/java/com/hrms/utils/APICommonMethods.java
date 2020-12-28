package com.hrms.utils;

import static io.restassured.RestAssured.given;

import io.restassured.specification.RequestSpecification;

public class APICommonMethods{

	/** Use this method to create an employee */
	/**
	 * Pass below parameters
	 * 
	 * @param token
	 * @param createEmployeeBody
	 * @return
	 */
	public static RequestSpecification createEmployeeRequest(String token, String createEmployeeBody) {
		RequestSpecification request = given().header("Authorization", token).header("Content-Type", "application/json")
				.body(createEmployeeBody);
		
		return request;
	}

	/** Use this method to GET and employee */
	/**
	 * 
	 * @param token
	 * @param employeeID
	 * @return
	 */
	public static RequestSpecification getOneEmployeeRequest(String token, String employeeID) {
		return given().header("Content-Type", "application/json").header("Authorization", token).param("employee_id",
				employeeID);
	}

	/** Use this method to generate a token */
	/**
	 * 
	 * @param generateTokenBody
	 * @return
	 */
	public static RequestSpecification generateTokenRequest(String generateTokenBody) {
		return given().header("Content-Type", "application/json").body(generateTokenBody);
	}
}