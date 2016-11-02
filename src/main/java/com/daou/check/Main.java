package com.daou.check;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;

import com.daou.check.service.module.Common;
import com.daou.check.service.module.Member;
import com.daou.check.service.module.Message;
import com.daou.check.service.suite.Basic;

public class Main {
	
	private static ChromeDriver chromeDriver = null;
	
	public static void main(String[] args) {
        setup();
        main();
        finish();
    }

	private static void setup() {
		setup_chromeDriver();
		setup_modules();
	}
	private static void main() {
		Basic.construct();
		Basic.run();
	}
	private static void finish() {
		chromeDriver.quit();
	}
	
	private static void setup_chromeDriver() {
		System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
		chromeDriver = new ChromeDriver();
		chromeDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		chromeDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		chromeDriver.manage().window().maximize();
	}
	private static void setup_modules() {
		Common.setChromeDriver(chromeDriver);
		Member.setChromeDriver(chromeDriver);
		Message.setChromeDriver(chromeDriver);
	}
}
