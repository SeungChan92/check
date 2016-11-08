package suite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import infra.Common;
import infra.Config;
import infra.Tool;

public class Suite {
	protected static WebDriver webDriver = null;
	
	// depth 0
	@BeforeClass
	public static void setUp() {
		setup_webDriver();
		setup_infra();
	}
	@AfterClass
	public static void finish() {
		webDriver.close();
	}
	
	// depth 1
	private static void setup_webDriver() {
			
		// # make
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
		webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		webDriver.manage().window().maximize();
	}
	private static void setup_infra() {
		Tool.init(webDriver);
		Config.init();
		Common.init(webDriver);
	}
}