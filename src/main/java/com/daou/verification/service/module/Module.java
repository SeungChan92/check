package com.daou.verification.service.module;

import org.openqa.selenium.WebDriver;

public abstract class Module {
	protected static WebDriver driver;
	
	public static void setWebDriver(WebDriver driver) {
		Module.driver = driver;
	}
}
