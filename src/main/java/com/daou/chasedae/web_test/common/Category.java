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
	protected ExtentTest logger;

	protected Logger log;

	protected Category(String category, WebDriver driver, String baseUrl, Tool tool, ExtentTest logger) {
		this.category = category;
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.tool = tool;
		this.logger = logger;
		
		log = LogManager.getRootLogger();
	}
	
	public ExtentTest getLogger() {
		return logger;
	}
}
