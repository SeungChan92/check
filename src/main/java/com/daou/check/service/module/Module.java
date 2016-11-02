package com.daou.check.service.module;

import org.openqa.selenium.chrome.ChromeDriver;

public abstract class Module {
	protected static ChromeDriver driver;
	
	public static void setChromeDriver(ChromeDriver chromeDriver) {
		Module.driver = chromeDriver;
	}
}
