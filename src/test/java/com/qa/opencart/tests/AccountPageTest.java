package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {

	
	@BeforeClass
	public void accPageSetup() {
		accountspge = loginpge.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test (priority =1)
	public void accPageTitleTest() {
		String accTitle = accountspge.getAccountsPageTitle();
		System.out.println("Account Page title is "+accTitle);
		Assert.assertEquals(accTitle, Constants.ACCOUNTS_PAGE_TITLE);
	}
	
	@Test (priority =2)
	public void accPageHeaderTest() {
		String header = accountspge.getAccountsPageHeader();
		System.out.println("Account Page Header is : "+header);
		Assert.assertEquals(header, Constants.ACCOUNTS_PAGE_HEADER);
	}
	
	@Test (priority =3)
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountspge.isLogoutLinkExist());		
	}
	
	@Test (priority =4)
	public void accountPageSectionsListTest() {
		List<String> sectionsList = accountspge.getAccountSecList();
		Assert.assertEquals(sectionsList, Constants.getExpectedAccountPageSectionsList());		
	}

	
	@DataProvider
	public Object[][] productData(){
		return new Object[][] {
			{"Macbook"},
			{"Apple"},
			{"Samsung"}
			};
	}

	@Test (priority =5, dataProvider = "productData")
	public void searchTest(String productName) {
		searchresultspge = accountspge.doSearch(productName);
		Assert.assertTrue(searchresultspge.getProductListCount() > 0);
	}

	@DataProvider
	public Object[][] productSelectData(){
		return new Object[][] {
			{"Macbook","MacBook Air"},
			{"Apple", "Apple Cinema 30\""},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"iMac", "iMac"}
			};
	}
	@Test(priority = 6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchresultspge = accountspge.doSearch(productName);
		productinfopge = searchresultspge.selectProduct(mainProductName);
		Assert.assertEquals(productinfopge.getProductHeader(),mainProductName);
	}
	
}
