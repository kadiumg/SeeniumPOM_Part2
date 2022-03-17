package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class productInfoPageTest extends BaseTest {

	@BeforeClass
	public void productPageSetUp() {
		accountspge = loginpge.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productHeaderTest()  {
		searchresultspge = accountspge.doSearch("MacBook");
		productinfopge = searchresultspge.selectProduct("MacBook Pro");
		Assert.assertEquals(productinfopge.getProductHeader(), "MacBook Pro");
	}
	
	@Test (priority = 2, enabled=true)
	public void productImagesCountTest() {
		searchresultspge = accountspge.doSearch("iMac");
		productinfopge = searchresultspge.selectProduct("iMac");
		Assert.assertEquals(productinfopge.getProductImagesCount(), Constants.IMAC_IMAGE_COUNT);
	}
	
	@Test (priority = 3)
	public void productOnfoTest() {
		searchresultspge = accountspge.doSearch("MacBook");
		productinfopge = searchresultspge.selectProduct("MacBook Pro");
		Map<String, String> actProductInfoMap = productinfopge.getProductInfo();
		actProductInfoMap.forEach((k,v) -> System.out.println(k+" : "+v));
		
		//Softassert will execute all the conditions even if some fails, Hard assertion will not execute if one fails
		softassert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
		softassert.assertEquals(actProductInfoMap.get("Productname"), "MacBook Pro");
		softassert.assertEquals(actProductInfoMap.get("Price"), "$2,000.00");
		softassert.assertAll();
	
	}
}
