package com.daou.verification;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.daou.verification.config.Config;
import com.daou.verification.service.module.Common;
import com.daou.verification.service.module.Member;
import com.daou.verification.service.module.Message;
import com.daou.verification.service.module.Tool;
import com.daou.verification.service.suite.BasicSuite;

public class Main {
	
	private static WebDriver webDriver = null;
	
	public static void main(String[] args) {
        setup();
        verify();
        finish();
    }

	// depth 1
	private static void setup() {
		setup_webDriver();
		setup_modules();
		setup_config();
	}
	private static void verify() {
		BasicSuite.construct();
		BasicSuite.run();
	}
	private static void finish() {
		webDriver.quit();
	}
	
	// depth 2
	private static void setup_webDriver() {
		
//		// # make
//		// ## [for] local test
//				System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
//				System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
//				webDriver = new InternetExplorerDriver();
		// ## [for] remote
		try {
			webDriver = new RemoteWebDriver(
					new URL("http://127.0.0.1:9515"),
			        DesiredCapabilities.chrome());
//			webDriver = new RemoteWebDriver(
//					new URL("http://127.0.0.1:5555"),
//			        DesiredCapabilities.internetExplorer());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		// # configure
		webDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
	}
	private static void setup_modules() {
		Tool.setWebDriver(webDriver);
		Common.setWebDriver(webDriver);
		Member.setWebDriver(webDriver);
		Message.setWebDriver(webDriver);
	}
	private static void setup_config() {
		Config.load();
	}
}
