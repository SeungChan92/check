package com.daou.chasedae.web_test.adppurio;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.daou.chasedae.web_test.common.Tool;
import com.daou.chasedae.web_test.common.Variables_Message;
import com.daou.chasedae.web_test.common.Variables_User;

public class Main_IE {
	private WebDriver driver;
	private String baseUrl;
	private Tool tool;
	
	private StringBuffer verificationErrors = new StringBuffer();
	
	private TestSuite testSuite;
	
	private Logger log = LogManager.getLogger("LevelLog");

	@Before
	public void setUp() throws Exception {
		baseUrl = "http://www-sta.adppurio.com:10051";
//		baseUrl = "https://www.adppurio.com";
		
		System.setProperty("webdriver.ie.driver", "C:/driver/IEDriverServer.exe");
		
		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
	    tool = new Tool(driver);
	    
	    testSuite = new TestSuite(driver, baseUrl, tool, "IE");
	}

	@Test
	public void main() throws Exception {
		log.log(Level.getLevel("BOUND"), "------------------------------------------------------------------------");
		log.log(Level.getLevel("BOUND"), "IE      - start");
		
		testSuite.ready();
		testSuite.test();
		
		log.log(Level.getLevel("BOUND"), "IE      - end");
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
