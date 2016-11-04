package all;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Test {
	private WebDriver webDriver = null;
	
	@org.junit.Test
	public void test() {
		this.setup_webDriver();
		webDriver.get("http://www.naver.com");
		webDriver.quit();
	}
	private void setup_webDriver() {
		
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
}
