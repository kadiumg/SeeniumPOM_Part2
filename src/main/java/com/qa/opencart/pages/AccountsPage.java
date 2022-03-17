package com.qa.opencart.pages;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	
	//1. Declare Private variables
	private WebDriver Driver3;
	private ElementUtil eleUtil1;
	
	//2. Initialize the Driver
	public AccountsPage(WebDriver Driver) {
		this.Driver3 = Driver;
		eleUtil1 = new ElementUtil(Driver);
	}
	
	//3. private By locators
	private By header = By.cssSelector("div#logo a");
	private By accountsSections = By.cssSelector("div#content h2");
	private By searchField = By.cssSelector("div#search input ");
	private By searchButton = By.cssSelector("div#search span button");
	private By logoutLink = By.linkText("Logout");
	
	
	//4. Page Actions
	public String getAccountsPageTitle() {
		return eleUtil1.doGetTitle(Constants.ACCOUNTS_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountsPageHeader() {
		return eleUtil1.doGetText(header);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil1.doIsDisplayed(logoutLink);
	}
	
	public void logout() {
		if(isLogoutLinkExist()) {
			eleUtil1.doClick(logoutLink);
	}
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> accSecList = eleUtil1.waitForElementsToBeVisible(accountsSections, 5);
		List<String> accSecListText = new ArrayList<String>();
		for(WebElement e: accSecList) {
			String text = e.getText();
			accSecListText.add(text);
		}
		return accSecListText;
	}
	public boolean isSearchExist() {
		return eleUtil1.doIsDisplayed(searchField);
	}
	
	public SearchResultsPage doSearch(String productName) {
		System.out.println("Searching for product: "+productName);
		eleUtil1.doSendKeys(searchField, productName);
		eleUtil1.doClick(searchButton);
		return new SearchResultsPage(Driver3);
	}
	

}
