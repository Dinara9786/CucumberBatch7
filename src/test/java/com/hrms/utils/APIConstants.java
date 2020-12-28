package com.hrms.utils;

import io.restassured.RestAssured;

public class APIConstants {
	
	public static final String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";

	public static final String GENERATE_TOKEN_URI = BaseURI + "/generateToken.php";

	public static final String CREATE_EMPLOYEE_URI = BaseURI + "/createEmployee.php";

	public static final String GET_ONE_EMPLOYEE_URI = BaseURI + "/getOneEmployee.php";

	public static final String UPDATE_EMPLOYEE_URI = BaseURI + "/updateEmployee.php";

	public static final String GET_ALL_EMPLOYEES_URI = BaseURI + "/getAllEmployees";

	public static final String CREATE_EMPLOYEE_JSON = System.getProperty("user.dir")
			+ "/src/test/resources/JSONFiles/createEmployee.json";
	
	public static final String GENERATE_TOKEN_JSON = System.getProperty("user.dir")
			+ "/src/test/resources/JSONFiles/generateToken.json";
	
}
