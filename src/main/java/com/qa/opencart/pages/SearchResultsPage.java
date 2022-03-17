package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class SearchResultsPage {
	
	//1. Declare Private Driver
	private WebDriver Driver4;
	private ElementUtil eleUtil;

	//2. Initialize the web Driver
	public SearchResultsPage(WebDriver driver3) {
		this.Driver4=driver3;
		eleUtil = new ElementUtil(Driver4);
	}
	
	//3. By locators
	private By productResults = By.cssSelector("div.caption a");

	
	//4. Page Functions
	public int getProductListCount() {
		int count = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000).size();
		System.out.println("The search Product results count is : "+count);
		return count;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("Main Productname is :"+mainProductName);
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(productResults, 10, 2000);
		for(WebElement e: searchList) {
			String text = e.getText().trim();
			if(text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(Driver4);
	}





}
