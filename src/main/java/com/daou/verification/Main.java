package com.daou.verification;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import com.daou.verification.service.module.Common;
import com.daou.verification.service.module.Member;
import com.daou.verification.service.module.Message;
import com.daou.verification.service.module.Tool;
import com.daou.verification.service.suite.BasicSuite;

public class Main {
	
	private static ChromeDriver chromeDriver = null;
	
	public static void main(String[] args) {
        setup();
        verify();
        finish();
    }

	// depth 1
	private static void setup() {
		setup_chromeDriver();
		setup_modules();
	}
	private static void verify() {
		BasicSuite.construct();
		BasicSuite.run();
	}
	private static void finish() {
		chromeDriver.quit();
	}
	
	// depth 2
	private static void setup_chromeDriver() {
		System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		chromeDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		chromeDriver.manage().window().maximize();
	}
	private static void setup_modules() {
		Tool.setChromeDriver(chromeDriver);
		Common.setChromeDriver(chromeDriver);
		Member.setChromeDriver(chromeDriver);
		Message.setChromeDriver(chromeDriver);
	}
}
