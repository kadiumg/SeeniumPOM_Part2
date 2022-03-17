package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	
	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String LOGIN_PAGE_URL_FRACTION = "route=account/login";
	public static final String ACCOUNTS_PAGE_TITLE = "My Account";
	public static final int DEFAULT_TIME_OUT = 5;
	public static final String ACCOUNTS_PAGE_HEADER = "Your Store11";
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final String LOGIN_ERROR_MSG = "No match for E-Mail Address and/or Password.";
	public static final String REGISTER_SUCCESS_MESSAGE = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "registration";

	public static List<String> getExpectedAccountPageSectionsList() {
		List<String> expAccSecList = new ArrayList<String>();
		expAccSecList.add("My Account");
		expAccSecList.add("My Orders");
		expAccSecList.add("My Affiliate Account");
		expAccSecList.add("Newsletter");	
		return expAccSecList;
	}

}
