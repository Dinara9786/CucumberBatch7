package com.hrms.pages;

import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import com.hrms.testbase.PageInitializer;

public class LoginPageElements extends PageInitializer{

	@FindBy(id = "txtUsername")
	public WebElement userNametextBox;
	
	@FindBy(xpath = "//input[@id = 'txtPassword']")
	public WebElement passwordTextBox;
	
	@FindBy(css = "input#btnLogin")
    public WebElement btnLogin;
	
	@FindBy(id = "divLogo")
	public WebElement syntaxLogo;
	
	@FindBy(id = "spanMessage1")
	public WebElement spanMessage;
	
	@FindBy(id = "logInPanelHeading")
	public WebElement loginPanelHeading;
	
	/**
	 * this method will login to hrms by the given username and password
	 * @param username
	 * @param password
	 */
	public void loginToHrms(String username, String password) {
		login.userNametextBox.sendKeys(username);
		login.passwordTextBox.sendKeys(password);
		login.btnLogin.click();
	}
	
	public LoginPageElements() {
		PageFactory.initElements(driver, this);
	}
	
}