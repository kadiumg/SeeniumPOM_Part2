package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	
	//1. Declare Private variables
	private WebDriver Driver5;
	private ElementUtil eleUtil;
	private Map<String,String> productinfoMap;
	
	//2. Initialize the Driver
	public ProductInfoPage(WebDriver Driver4) {
		this.Driver5 = Driver4;
		eleUtil = new ElementUtil(Driver5);
	}
	//3. private By locators
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productImages = By.xpath("//a[@class='thumbnail']//img");
	private By productMetaData = By.xpath("((//div[@class='col-sm-4'])[2]/ul)[1]/li");
	private By productPriceData = By.xpath("((//div[@class='col-sm-4'])[2]/ul)[2]/li");
	private By qty = By.xpath("//input[@id='input-quantity']");
	private By addToCartBtn = By.xpath("//button[@id='button-cart']");
		
	
	//4. Page Actions
	public String getProductHeader() {
		String heading = Driver5.findElement(productHeader).getText();
		return heading;
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, 10, 2000).size();
	}
	
	public Map<String, String> getProductInfo() {
		productinfoMap = new LinkedHashMap<String, String>();
		productinfoMap.put("Productname", getProductHeader());
		getProductMetaData();
		getProductPriceData();
		return productinfoMap;
	}
	
	private void getProductMetaData() {
		List<WebElement> metaDataList = eleUtil.getElements(productMetaData);
		for(WebElement e : metaDataList) {
			String text = e.getText();
			String meta[] = text.split(":");
			String metakey = meta[0].trim();
			String metavalue = meta[1].trim();
			productinfoMap.put(metakey, metavalue);
		}
	
	}
	
	private void getProductPriceData() {
		List<WebElement> metaPriceList = eleUtil.getElements(productPriceData);
		String price = metaPriceList.get(0).getText();
		String exPrice = metaPriceList.get(1).getText();
		productinfoMap.put("Price", price);
		productinfoMap.put("ExPrice", exPrice);
	}

}


