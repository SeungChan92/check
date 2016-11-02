package com.daou.check;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import com.daou.check.service.module.Common;
import com.daou.check.service.module.Member;
import com.daou.check.service.module.Message;
import com.daou.check.service.module.Tool;
import com.daou.check.service.suite.BasicSuite;

public class Main {
	
	private static ChromeDriver chromeDriver = null;
	
	public static void main(String[] args) {
        setup();
        main();
        finish();
    }

	// depth 1
	private static void setup() {
		setup_chromeDriver();
		setup_modules();
	}
	private static void main() {
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
