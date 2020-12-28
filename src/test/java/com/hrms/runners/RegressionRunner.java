package com.hrms.runners;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)  // I am running it with Cucumber
@CucumberOptions(features = "src/test/resources/features/", // need to give a path for our features files
					glue = "com/hrms/stepDefinitions", // we need to glue our step definitions - implementation
					dryRun = true, // when set as true, will run over the feature steps and identify the missing implementation 
					monochrome = true,// when set as true, will format the console outcome
					tags = "@regression",
					strict = false, // when set as true, will fail the execution when undefined step is found
					plugin = {"pretty", // pretty will print all my steps inside console
							"html:target/cucumber-default-reports", // generates default html report
							"rerun:target/FailedTests.txt", // generates a txt file only with failed tests
							"json:target/cucumber.json"} // generates json reports
							)


public class RegressionRunner {

}
