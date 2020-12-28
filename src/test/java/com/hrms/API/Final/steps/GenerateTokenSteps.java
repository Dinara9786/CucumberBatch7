package com.hrms.API.Final.steps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import com.hrms.utils.APICommonMethods;
import com.hrms.utils.APIConstants;
import com.hrms.utils.CommonMethods;

public class GenerateTokenSteps {
	
	//String BaseURI = RestAssured.baseURI = "http://18.232.148.34/syntaxapi/api";
	static public String token;
	
	@Given ("a JWT is generated")
	public void a_JWt_is_generated() {
		
		/** Preparing request to generate token*/
		
		RequestSpecification generateTokenRequest = APICommonMethods
		.generateTokenRequest(CommonMethods.readJson(APIConstants.GENERATE_TOKEN_JSON));
		
		/** Storing response into generateTokenResponse*/
		Response generateTokenResponse =generateTokenRequest.when().post(APIConstants.GENERATE_TOKEN_URI);
		
		/** Optional to print response */
		generateTokenResponse.prettyPrint();
		
		/** 
		 * Storing token as a static global variable that will be used for other call
		 */
		token = "Bearer " + generateTokenResponse.jsonPath().getString("token");
	}

}

