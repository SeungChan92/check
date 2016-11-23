package suite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import infra.Common;
import infra.Config;
import infra.Tool;

public abstract class Suite {
	protected static WebDriver driver = null;
	
	// depth 0
	@BeforeClass
	public static void setUp() {
		setup_driver();
		setup_infra();
	}
	@AfterClass
	public static void finish() {
		driver.close();
	}
	
	// depth 1
	private static void setup_driver() {
			
		// # make
		// ## [for] local test
				System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
				System.setProperty("webdriver.ie.driver", "driver/IEDriverServer.exe");
//				driver = new InternetExplorerDriver();
				driver = new ChromeDriver();
//		// ## [for] remote
//		try {
//			driver = new RemoteWebDriver(
//					new URL("http://127.0.0.1:9515"),
//			        DesiredCapabilities.chrome());
////			driver = new RemoteWebDriver(
////					new URL("http://127.0.0.1:5555"),
////			        DesiredCapabilities.internetExplorer());
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
		
		// # configure
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	private static void setup_infra() {
		Tool.init(driver);
		Config.init();
		Common.init(driver);
	}
}