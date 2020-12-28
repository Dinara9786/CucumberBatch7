package com.hrms.stepDefinitions;

import java.util.List;
import java.util.Map;

import com.hrms.utils.CommonMethods;
import com.hrms.utils.DBUtils;
import com.hrms.utils.GlobalVariables;

import io.cucumber.java.en.Then;

public class DBStepDefinitions extends CommonMethods{
	
	
	public static List<Map<String, String>> dbData;
	
	@Then("get first name from the db")
	public void get_first_name_from_the_db() {
		String sqlQuery = "select emp_firstname from hs_hr_employees where employee_id = " + GlobalVariables.empId;
		dbData = DBUtils.storeDataFromDB(sqlQuery);
		DBUtils.closeConnection();
	}

}