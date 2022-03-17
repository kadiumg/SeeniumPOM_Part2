package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationPageTest extends BaseTest {

	@BeforeTest
	public void setupRegistration() {
		registerpge = loginpge.goToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegisterData(){
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegisterData")
	public void userRegistrationTest(String firstName, String lastName, String eMail, String Telephone,
			String password, String subscribe) {
		Assert.assertTrue(registerpge.accountRegistraton(firstName, lastName, eMail, Telephone,
				password, subscribe));
	}
	
}
