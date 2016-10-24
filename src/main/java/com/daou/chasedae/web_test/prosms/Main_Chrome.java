package com.daou.chasedae.web_test.prosms;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import com.daou.chasedae.web_test.common.Tool;
import com.daou.chasedae.web_test.common.Variables_Message;
import com.daou.chasedae.web_test.common.Variables_User;

public class Main_Chrome {
	private WebDriver driver;
	private String baseUrl;
	private Tool tool;
	
	private StringBuffer verificationErrors = new StringBuffer();
	
	private TestSuite testSuite;
	
	private Logger log = LogManager.getLogger("LevelLog");

	@Before
	public void setUp() throws Exception {

		baseUrl = "www-dev.prosms.co.kr:10051";
		
		System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
		
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		tool = new Tool(driver, baseUrl);
	    
	    testSuite = new TestSuite(driver, baseUrl, tool, "Chrome");
	}

	@Test
	public void main() throws Exception {
		log.log(Level.getLevel("BOUND"), "------------------------------------------------------------------------");
		log.log(Level.getLevel("BOUND"), "Chrome  - start");
		
		testSuite.ready();
		testSuite.test();
		
		log.log(Level.getLevel("BOUND"), "Chrome  - end");
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