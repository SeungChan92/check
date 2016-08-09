package com.daou.chasedae.web_test.ppurio;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.daou.chasedae.web_test.common.Tool;

public class Main_IE {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();
	
	private Tool wait;

	@Before
	public void setUp() throws Exception {
		baseUrl = "http://www.ppurio.com/mgr/index.qri";
		
		System.setProperty("webdriver.ie.driver", "C:/driver/IEDriverServer.exe");
		
		driver = new InternetExplorerDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		
		// init WebDriverWait
	    wait = new Tool(driver);
	}

	@Test
	public void main() throws Exception {
		// make classes
		Member member = new Member(driver, baseUrl, wait);
		Message message = new Message(driver, baseUrl, wait);
		
		// member
		//member.login();
		//member.logout();
		//member.findId_ByMobile();
		member.findId_ByEmail();
		
		// message

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
