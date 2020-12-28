package com.hrms.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)  // I am running it with Cucumber
@CucumberOptions(features = "@target/FailedTests.txt", // need to give a path for our features files
					glue = "com/hrms/stepDefinitions", // we need to glue our step definitions - implementation
					monochrome = true,// when set as true, will format the console outcome
					plugin = {"pretty", // pretty will print all my steps inside console
							"html:target/cucumber-default-reports", // generates default html report
							"rerun:target/FailedTests.txt"} // generates a txt file only with failed tests
							)

public class FailedRunner {
	
	

}
