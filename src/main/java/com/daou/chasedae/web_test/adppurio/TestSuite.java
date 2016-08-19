package com.daou.chasedae.web_test.adppurio;

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
		
		variables_message = (JSONObject) parser.parse(new FileReader(
				"/data/variables/message.json"));
//		Variables_SendNumber.make();
	}

	public void test() {

		try {			
			member.login(Variables_User.id, Variables_User.pw);
			member.editUserInfo();
			message.suite();
			//			// member
			//			member.login(Variables_User.id, Variables_User.pw);
			//			member.logout();			
			//			member.findID_ByMobile();
			//			member.findID_ByEmail();
			//			member.findID_ByIdentity();
			//			member.findPW_ByMobile();
			//			member.findPW_ByEmail();
			//			member.findPW_ByIdentity();
			//
//			member.login(Variables_User.id, Variables_User.pw);
			//
			//			// member - 마이페이지
			//			member.editUserInfo();
			//			member.editPW(Variables_User.pw);
			//			member.registerSendNumber();
			//
			//			// member - 결제관리
			//			member.pay_ByMobile();
			//			if(!browser.equals("firefox"))
			//			{
			//				member.pay_ByBank();
			//			}
			//			member.bankBook();
			//
			//			//address
			//			address.addGroup("new_group");
			//			address.addAddress();
			//			address.addAddress_InManagement();
			//			address.editAddress("오우석", "01011111111", "022222222", "0322222222", "issea1015@gmail.com");
			//			address.editAddress("안승찬", "01029591783", "022211122", "0322222222", "daou@gmail.com");
			//			address.sendAddress();
			//			address.sendAddress_check();
			//			address.printAddress_Preshow();
			//
			//			// message
			//			String message_content = "";
			//			
			//			for(int i=0;i<2;i++)
			//			{
			//				if(i==0)
			//				{
			//					message_content = Variables_Message.content_short;
			//				}
			//				else if(i==1)
			//				{
			//					message_content = Variables_Message.content_long_150;
			//				}
			//
//			message.suite();
			//	
			//				message.init("장문");
			//				if(!browser.equals("firefox"))
			//				{
			//					message.loadAddress_FromTextFile("C:\\Users\\daou\\Desktop\\주소록\\1만.txt");
			//				}
			//				message.reserve("25", "10", "10");
			//				message.typeTitle(Variables_Message.title);
			//				message.typeMessage(message_content);
			//				message.send();
			//	
			//				message.init("포토");
			//				message.addSpecialChar();
			//				message.typeTitle(Variables_Message.title);
			//				message.typeMessage(message_content);
			//				message.send();
			//			}
			//
			//			//address
			//			address.deleteAddress();
			//			address.deleteGroup();
			//
		} catch(Fail fail) {
			//			logger.log(LogStatus.FAIL, fail.buildMessage());
			//			log.error(fail.buildMessage());
		} catch(Exception e) {
			e.printStackTrace();
			//			log.error("need to specify error");
		}

//		extentTest.appendChild(message.getExtentTest());
//		extentTest.appendChild(address.getExtentTest());
//		extentTest.appendChild(member.getExtentTest());
//		reports.endTest(extentTest);
//		reports.flush();
//		reports.close();
	}

}
