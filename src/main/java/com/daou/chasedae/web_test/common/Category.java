package com.daou.chasedae.web_test.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentTest;

public class Category {
	protected final String category;
	
	protected WebDriver driver;
	protected String baseUrl;
	protected Tool tool;
	protected ExtentTest extentTest;

	protected Logger logger;

	protected Category(String category, WebDriver driver, String baseUrl, Tool tool, ExtentTest extentTest) {
		this.category = category;
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.tool = tool;
		this.extentTest = extentTest;
		
		this.logger = LogManager.getRootLogger();
	}
	
	public ExtentTest getExtentTest() {
		return extentTest;
	}
}
