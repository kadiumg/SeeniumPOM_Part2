package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {

	//1. Declare Private variables
	private WebDriver Driver;
	private ElementUtil eleUtil;

	//2. Initialize the web Driver
	public RegisterPage(WebDriver Driver1) {
		this.Driver = Driver1;
		eleUtil = new ElementUtil(Driver1);
	    }
	//3. private By locators
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By eMail = By.id("input-email");
	private By Telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By passwordConfirm = By.id("input-confirm");
	private By SubscribeYes = By.xpath("(//label[@class='radio-inline'])[1]");
	private By SubscribeNo = By.xpath("(//label[@class='radio-inline'])[2]");
	
	private By privacyCheckbox = By.xpath("//input[@type='checkbox']");
	private By continueButton = By.xpath("//input[@value='Continue']");

	private By successMsg = By.xpath("//div[@id='content']/h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");	
		
	//4. Page Actions		
		public boolean accountRegistraton(String firstName, String lastName, String eMail, String Telephone,
										String password, String subscribe) {
			
			eleUtil.doSendKeys(this.firstName, firstName);
			eleUtil.doSendKeys(this.lastName, lastName);
			eleUtil.doSendKeys(this.eMail, eMail);
			eleUtil.doSendKeys(this.Telephone, Telephone);
			eleUtil.doSendKeys(this.password, password);
			eleUtil.doSendKeys(this.passwordConfirm, password);
			
			if(subscribe.equalsIgnoreCase("Yes")) {
				eleUtil.doClick(SubscribeYes);
			}else {
				eleUtil.doClick(SubscribeNo);
			}
			
			eleUtil.doClick(privacyCheckbox);
			eleUtil.doClick(continueButton);
			String successMessage = eleUtil.waitForElementToBeVisible(successMsg, 5, 1000).getText();
			if(successMessage.contains(Constants.REGISTER_SUCCESS_MESSAGE)) {
				eleUtil.doClick(logoutLink);
				eleUtil.doClick(registerLink);			
			return true;
			}
			return false;
			}
	}


