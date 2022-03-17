package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	
	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
				{"tst@test.com","test@345"},
				{"","Errrr"},
				{"gokul@gmail.com",""},
				{"",""}				
		};
	}
	
	@Test(dataProvider="loginWrongTestData")
	public void loginNegativeTest(String un, String pw) {
		
		Assert.assertFalse(loginpge.doLoginWithWrongCredentials(un, pw));
	}
	
}
