package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {

	
	WebDriver Driver;
	Properties prop;
	DriverFactory df;
	LoginPage loginpge;
	AccountsPage accountspge;
	SearchResultsPage searchresultspge;
	ProductInfoPage productinfopge;
	RegisterPage registerpge;
	SoftAssert softassert;
	
	@BeforeTest
	public void setup() {
		df = new DriverFactory();
		prop = df.init_prop();
		Driver = df.init_driver(prop);		
		loginpge = new LoginPage(Driver);
		softassert = new SoftAssert();
	}
	
	@AfterTest
	public void tearDown() {
		Driver.quit();
	}
}
