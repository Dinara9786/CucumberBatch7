package com.hrms.utils;

import org.json.JSONObject;

public class APIPayloadCommonMethods {

	public static String createEmployeeBody() {

		String createEmployeeBody = "{\r\n" + "  \"emp_firstname\": \"Jane\",\r\n"
				+ "  \"emp_lastname\": \"Lucky\",\r\n" + "  \"emp_middle_name\": \"Batch\",\r\n"
				+ "  \"emp_gender\": \"F\",\r\n" + "  \"emp_birthday\": \"1999-07-27\",\r\n"
				+ "  \"emp_status\": \"Freelance\",\r\n" + "  \"emp_job_title\": \"Network Administrator\"\r\n" + "}";

		return createEmployeeBody;

	}

	public static String createEmployeePayload() {

		JSONObject obj = new JSONObject();

		obj.put("emp_firstname", "Jane");
		obj.put("emp_lastname", "Lucky");
		obj.put("emp_middle_name", "Batch");
		obj.put("emp_gender", "F");
		obj.put("emp_birthday", "1999-07-27");
		obj.put("emp_status", "Freelance");
		obj.put("emp_job_title", "Network Administrator");

		return obj.toString();

	}

	public static String createEmployeePayloadMoreDynamic(String firstName, String lastName, String middleName,
			String gender, String dob, String employeeStatus, String employeeJobTitle) {

		JSONObject obj = new JSONObject();

		obj.put("emp_firstname", firstName);
		obj.put("emp_lastname", lastName);
		obj.put("emp_middle_name", middleName);
		obj.put("emp_gender", gender);
		obj.put("emp_birthday", dob);
		obj.put("emp_status", employeeStatus);
		obj.put("emp_job_title", employeeJobTitle);

		return obj.toString();

	}
}
