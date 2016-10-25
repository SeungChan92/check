package com.daou.chasedae.web_test.gosms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import com.daou.chasedae.web_test.common.Fail;
import com.daou.chasedae.web_test.common.Phone;
import com.daou.chasedae.web_test.common.RandomString;
import com.daou.chasedae.web_test.common.Tool;
import com.daou.chasedae.web_test.common.Variables_Message;
import com.daou.chasedae.web_test.common.Variables_SendNumber;
import com.daou.chasedae.web_test.common.Variables_User;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestSuite {

	private Member member;
	private Address address;
	private Message message;
	private String browser;

	private Logger log = LogManager.getLogger("LevelLog");
	private Logger logger = LogManager.getRootLogger();
	private static ExtentReports reports = new ExtentReports("logs/[test_result]_adppurio.html", false);
	private ExtentTest extentTest;
	
	public static JSONObject variables_message;

	public TestSuite(WebDriver driver, String baseUrl, Tool tool, String browser) {
		tool.rememberMainWindow();
		reports.loadConfig(new File("src/main/resources/extent-config.xml"));
		extentTest = reports.startTest(browser);

		member = new Member(driver, baseUrl, tool, reports.startTest("Member"));
		address = new Address(driver, baseUrl, tool, reports.startTest("Address"));
		message = new Message(driver, baseUrl, tool, reports.startTest("Message"));
		this.browser = browser;
	}

	public void ready() throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		
		//variables_message = (JSONObject) parser.parse(new FileReader(
		//		"/data/variables/message.json"));
//		Variables_SendNumber.make();
	}

	public void test() throws Exception, Fail {

		member.login("yjlee2", "test1234##");
		
		/*
		for(int i=0; i<2000; i++)
		{
			String mobile = Phone.generate();
			address.addAddress("selenium", "안승찬", mobile);
		}
		*/
		
		RandomString randomString = new RandomString(10);
		for(int i=0; i<10000; i++)
		{
			String receiverNumber = Phone.generate();
			String title = randomString.nextString();
			String message_string = randomString.nextString();
			String mode = randomString.select_mode();
			
			try {
				message.send(mode, "예약", title, message_string, receiverNumber);
			} catch (Exception e) {
				System.out.println("Something goes wrong. But keep going.");
			}
		}
	}
}
