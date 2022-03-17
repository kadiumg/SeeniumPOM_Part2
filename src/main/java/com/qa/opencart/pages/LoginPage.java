package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	//1. Declare Private Driver
	private WebDriver Driver1;
	private ElementUtil eleUtil;
	
	//2. Initialize the web Driver
	public LoginPage(WebDriver Driver) {
		this.Driver1 = Driver;
		eleUtil = new ElementUtil(Driver);
	}
	
	//3. By locators
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By LoginButton = By.xpath("//input[@value='Login']");
	private By RegisterLink = By.linkText("Register");
	private By ForgotPassword = By.linkText("Forgotten Password");
	private By loginError = By.xpath("//div[@class='alert alert-danger alert-dismissible']");

	
	//4. Page Actions
	
	//@Step is coming from Allure Report
	
	@Step("Checking LP Title")
	public String getLoginPageTitle() {
		return Driver1.getTitle();
	}
	@Step("Checking LP URL")
	public String getLoginPageURL() {
		return Driver1.getCurrentUrl();
	}
	
	@Step("Checking LP forgot password link")
	public boolean isForgotPwdLinkExist() {
		return Driver1.findElement(ForgotPassword).isDisplayed();
	}
	
	@Step("Checking LP register link")
	public boolean isRegisterLinkExist() {
		return Driver1.findElement(RegisterLink).isDisplayed();
	}
	
	@Step("Do login with Username:{0} and Password:{1}")
	public AccountsPage doLogin(String un, String pw) {
		System.out.println("Login with "+un+" : "+pw);
		eleUtil.getElement(emailId).sendKeys(un);
		eleUtil.getElement(password).sendKeys(pw);
		eleUtil.getElement(LoginButton).click();
		return new AccountsPage(Driver1);
	}
	
	@Step("Do login with wrong Username:{0} and Password:{1}")
	public boolean doLoginWithWrongCredentials(String un, String pw) {
		eleUtil.getElement(emailId).sendKeys(un);
		eleUtil.getElement(password).sendKeys(pw);
		eleUtil.getElement(LoginButton).click();
		String errormsg = eleUtil.getElement(loginError).getText();
		System.out.println("Error Message is :"+errormsg);
		if(errormsg.contains(Constants.LOGIN_ERROR_MSG)) {
			return false;
		}
		return true;
	}
	
	@Step("Navigating to Register page")
	public RegisterPage goToRegisterPage() {
		eleUtil.getElement(RegisterLink).click();
		return new RegisterPage(Driver1);
	}
}
