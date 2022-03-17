package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.opencart.listeners.TestAllureListener;
import com.qa.opencart.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("EPIC 100: Design open cart App - Login Page")
@Story("US101: Open cart login design with multiple features")
@Listeners(TestAllureListener.class)
public class LoginPageTest extends BaseTest {

	//@Epic, @Story, @Description and Severity is Allure Reporting feature
	
	@Description("LP Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String LPtitle = loginpge.getLoginPageTitle();
		System.out.println("Login Page Title is : "+LPtitle);
		Assert.assertEquals(LPtitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("LP URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String LPUrl = loginpge.getLoginPageURL();
		System.out.println("Login Page URL is: "+LPUrl);
		Assert.assertTrue(LPUrl.contains(Constants.LOGIN_PAGE_URL_FRACTION));
	}
	@Description("LP Pwd link Test")
	@Severity(SeverityLevel.CRITICAL)	
	@Test(priority = 3)
	public void forgtPwdLinkTest() {
		Assert.assertTrue(loginpge.isForgotPwdLinkExist());
	}

	@Description("LP register link Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void registerLinkTest() {
		Assert.assertTrue(loginpge.isRegisterLinkExist());
	}

	@Description("LP Login Test")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 5)
	public void doLoginTest() {
		accountspge = loginpge.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountspge.getAccountsPageTitle(), Constants.ACCOUNTS_PAGE_TITLE);
	}	
}
