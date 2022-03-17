package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver Driver;
	Properties prop;
	OptionsManager optionsmanager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
		
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		
		System.out.println("The Browser Name is : "+browserName);
		
		highlight = prop.getProperty("highlight");	
		optionsmanager = new OptionsManager(prop);
		
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			Driver = new ChromeDriver(optionsmanager.getchromeoptions());
			tlDriver.set(Driver);
			
		}else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			Driver = new FirefoxDriver(optionsmanager.getfirefoxoptions());
			tlDriver.set(Driver);
			
		}else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			Driver = new EdgeDriver();
			tlDriver.set(Driver);
		}else
			System.out.println("Please pass the right browser :"+prop);
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		
		return getDriver();
	}

	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	public Properties init_prop() {
		prop = new Properties();
		FileInputStream ip = null;
		
		String envName = System.getProperty("env");  //qa/dev/stage/uat
		
		if(envName == null) {
			try {
				ip = new FileInputStream("./src/test/resources/config/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			
			System.out.println("Running on environment: "+envName);
			switch (envName) {
				case "qa":
				try {
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				case "uat":
				try {
					ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
					break;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				case "dev":
				try {
					ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
					break;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
				case "stage":
				try {
					ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
					break;
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
				default:
					System.out.println("Please pass right environment.......");
			}
		
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	//Take Screenshot
	public String getScreenshot() {
		File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
}
