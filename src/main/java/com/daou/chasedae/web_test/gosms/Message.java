package com.daou.chasedae.web_test.gosms;

import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.daou.chasedae.dataStructure.InputTag;
import com.daou.chasedae.web_test.common.Category;
import com.daou.chasedae.web_test.common.Data;
import com.daou.chasedae.web_test.common.Fail;
import com.daou.chasedae.web_test.common.Parameter;
import com.daou.chasedae.web_test.common.Tool;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Message extends Category {

	public Message(WebDriver driver, String baseUrl, Tool tool, ExtentTest extentTest) {
		super("Message", driver, baseUrl, tool, extentTest);
	}
	public void init(String mode) throws Fail {
		String url_sms = "mgr/PPSmsMgr.qri?act=sms_form";
		String url_lms = "mgr/PPSmsMgr.qri?act=smsform_mms";
		String url_mms = "mgr/PPPhotoMgr.qri?act=photo_form";
		String url = "";
		
		if (mode.equals("단문"))
		{
			url = url_sms;
		}
		else if (mode.equals("장문"))
		{
			url = url_lms;
		}
		else if (mode.equals("포토"))
		{
			url = url_mms;
		}
		
		try {
			driver.get(baseUrl + "/" + url);

			logger.info("message - mode : " + mode);

			//extentTest.log(LogStatus.PASS, "init");
			
		} catch (Exception e) {
			System.out.println(ExceptionUtils.getStackTrace(e));
			extentTest.log(LogStatus.ERROR
					, "init<br>"
							+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
			//			System.out.println(e.toString());
			//			Parameter[] parameters = new Parameter[1];
			//			parameters[0] = new Parameter("mode", mode);
			//			throw new Fail(category, "init(String mode)", parameters, e);
		}
	}
	public void typeTitle(String title) throws Fail {
		try {
			driver.findElement(By.name("subject")).clear();
			driver.findElement(By.name("subject")).sendKeys(title);

			//extentTest.log(LogStatus.PASS, "typeTitle");
		} catch (Exception e) {
			extentTest.log(LogStatus.ERROR
					, "typeTitle<br>"
							+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
		}
	}
	public void typeMessage(String message) throws Fail {
		try {
			driver.findElement(By.name("body1")).clear();
			driver.findElement(By.name("body1")).sendKeys(message);

			//extentTest.log(LogStatus.PASS, "typeMessage");
		} catch (Exception e) {
			System.out.println(e.toString());
			Parameter[] parameters = new Parameter[1];
			parameters[0] = new Parameter("message", message);
			throw new Fail(category, "typeMessage(String message)", parameters, e);
		}
	}
	public void send(String mode, String reserved, String title, String message, String receiverNumber) throws Fail {
		By button_send = By.id("sbutton");
		By button_send_2 = By.xpath("//a[@href='javascript:jsSend();']");
		By complete = By.className("sub_title");
		
		try {
			
			this.init(mode);
			
			if(mode.equals("포토"))
			{
				this.register_image();
			}
			this.typeTitle(title);
			this.typeMessage(message);
			this.loadAddress_FromType(receiverNumber);
			this.reserve("12", "31", "12", "0");
					
			driver.findElement(button_send).click();
			//tool.wait.until(ExpectedConditions.visibilityOf(driver.findElement(complete)));
			driver.findElement(button_send_2).click();
			//tool.observe_click(button_send);
					
			// workspace : end
			
			//tool.waitFor_allDone();
			
			//logger.debug("Message - send() : end");
			
			//extentTest.log(LogStatus.PASS, "send");


		} catch (Exception e) {
			logger.debug("Message - send() : error");
			e.printStackTrace();
			
//			extentTest.log(LogStatus.ERROR
//					, "send<br>"
//							+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
			//			Parameter[] parameters = new Parameter[0];
			//			throw new Fail(category, "send()", parameters, e);
		}
	}
	private void register_image() throws InterruptedException {
		By button_1 = By.xpath("//span[@id='image']//a");
		By input_filePath = By.name("userfile1");
		By button_include = By.xpath("//a[@href='javascript:jsImageUpload();']");
		
		driver.findElement(button_1).click();
		tool.goTo_PopUp();
		driver.findElement(input_filePath).sendKeys("C:/Users/Public/Pictures/Sample Pictures/사과.jpg");
		driver.findElement(button_include).click();
		tool.waitFor_alert();
		tool.closeAlert_andGetItsText();
		tool.goTo_main();
		
	}
	public void loadAddress_FromGroup(String group_name) throws Fail {
		try {
			driver.get(baseUrl + "/send/standard/sms");
			driver.findElement(By.id("btnSendReceiverAddress")).click();
			tool.goTo_PopUp();
			driver.findElement(By.xpath("(//a[text()='" + group_name + "']/../../..//input[@type='checkbox'])")).click();
			driver.findElement(By.id("btnSendReceiverAddressSubmit")).click();
			tool.waitFor_alert();
			Thread.sleep(5000);
			tool.closeAlert_andGetItsText();
			//assertTrue(tool.closeAlertAndGetItsText().matches("^선택한 1건을 발송창에 추가 하시겠습니까[\\s\\S]$"));
			driver.findElement(By.id("btnSendReceiverClose")).click();
			tool.goTo_main();

			extentTest.log(LogStatus.PASS, "loadAddress_FromGroup");
		} catch (Exception e) {
			System.out.println(ExceptionUtils.getStackTrace(e));
			extentTest.log(LogStatus.ERROR
					, "loadAddress_FromGroup<br>"
							+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
			//			Parameter[] parameters = new Parameter[1];
			//			parameters[0] = new Parameter("group_name", group_name);
			//			throw new Fail(category, "loadAddress_FromGroup(String group_name)", parameters, e);
		}
	}
	public void loadAddress_FromType(String receiverNumber) throws Fail {
		By button_add = By.xpath("//a[@href='javascript:addSmsList();']");
		
		driver.findElement(By.name("rcv_mobile")).click();
		driver.findElement(By.name("rcv_mobile")).sendKeys(receiverNumber);
		driver.findElement(button_add).click();
	}
	public void loadAddress_FromType_paste(String sendNumber) throws Fail {
		try {
			StringSelection selection = new StringSelection(sendNumber);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(selection, selection);
			//driver.findElement(By.id("textReceiverInput")).sendKeys("test");
			//Thread.sleep(5000);
			//driver.findElement(By.id("textReceiverInput")).click();
			//driver.findElement(By.id("textReceiverInput")).sendKeys(sendNumber);
			driver.findElement(By.id("textReceiverInput")).click();
			driver.findElement(By.id("textReceiverInput")).sendKeys(Keys.chord(Keys.CONTROL, "v"), "");
			Thread.sleep(5000);
			driver.findElement(By.id("btnReceiverAdd")).click();
			tool.waitFor_alert();
			tool.closeAlert_andGetItsText();
			//assertEquals("1건의 번호가 추가되었습니다.\n(중복되거나 유효하지않은 번호는 삭제됩니다.)", tool.closeAlertAndGetItsText());
		} catch (Exception e) {
			Parameter[] parameters = new Parameter[1];
			parameters[0] = new Parameter("sendNumber", sendNumber);
			throw new Fail(category, "loadAddress_FromType(String sendNumber)", parameters, e);
		}
	}
	public void loadAddress_FromTextFile(String file_path) throws Fail {
		try {
			driver.get(baseUrl + "/send/standard/sms");
			driver.findElement(By.id("btnSendReceiverAddress")).click();
			tool.goTo_PopUp();
			// ERROR: Caught exception [ERROR: Unsupported command [waitForPopUp | PopupSendReceiver | 30000]]
			// ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=PopupSendReceiver | ]]
			driver.findElement(By.linkText("텍스트파일")).click();
			driver.findElement(By.id("uploadFile")).clear();
			driver.findElement(By.id("uploadFile")).sendKeys(file_path);
			Thread.sleep(1000);
			driver.findElement(By.id("btnSendReceiverFileSubmit")).click();
			tool.goTo_main();
		} catch (Exception e) {
			Parameter[] parameters = new Parameter[1];
			parameters[0] = new Parameter("file_path", file_path);
			throw new Fail(category, "loadAddress_FromTextFile(String file_path)", parameters, e);
		}
	}
	public void reserve(String month, String day, String hour, String minute) throws Fail {
		
		By radio_reserve = By.xpath("//input[@onclick='jsReserveCheck(1)']");
		By select_month	= By.name("month");
		By select_day	= By.name("day");
		By select_hour	= By.name("hour");
		By select_minute = By.name("minute");
		
		//try {
			driver.findElement(radio_reserve).click();
			tool.selectByValue(select_month, month);
			tool.selectByValue(select_day, day);
			tool.selectByValue(select_hour, hour);
			tool.selectByValue(select_minute, minute);
		//} catch (Exception e) {
			/*Parameter[] parameters = new Parameter[3];
			parameters[0] = new Parameter("day", day);
			parameters[1] = new Parameter("hour", hour);
			parameters[2] = new Parameter("minute", minute);
			throw new Fail(category, "reserve(String day, String hour, String minute)", parameters, e);*/
		//}
	}
	public void addSpecialChar() throws Fail {
		try {
			driver.get(baseUrl + "/send/standard/mms");
			driver.findElement(By.id("btnSendSpecialChar")).click();
			driver.findElement(By.linkText("♥")).click();
			driver.findElement(By.linkText("♡")).click();
			driver.findElement(By.linkText("★")).click();
			driver.findElement(By.linkText("☆")).click();
			driver.findElement(By.linkText("▒")).click();
			driver.findElement(By.linkText("♨")).click();
			driver.findElement(By.linkText("※")).click();
			driver.findElement(By.linkText("◆")).click();
			driver.findElement(By.linkText("◇")).click();
			driver.findElement(By.linkText("♣")).click();
			driver.findElement(By.linkText("†")).click();
			driver.findElement(By.linkText("3")).click();
			driver.findElement(By.linkText("◐")).click();
			driver.findElement(By.linkText("닫기")).click();
		} catch (Exception e) {
			Parameter[] parameters = new Parameter[0];
			throw new Fail(category, "addSpecialChar()", parameters, e);
		}
	}
	public void saveMessage() throws Fail {
		try {
			driver.findElement(By.id("btnSendMessageboxSave")).click();
			tool.waitFor_alert();
			assertTrue(tool.closeAlert_andGetItsText().matches("^메시지를 저장하시겠습니까[\\s\\S]$"));
			tool.waitFor_alert();
			assertEquals("저장하였습니다.", tool.closeAlert_andGetItsText());
			Thread.sleep(1000);

			extentTest.log(LogStatus.PASS, "saveMessage");
		} catch (Exception e) {
			Parameter[] parameters = new Parameter[0];
			throw new Fail(category, "saveMessage()", parameters, e);
		}
	}

	public void suite() throws Fail {
		logger.debug("Message - suite() : start");
		
		String[] modes = {"단문"};
		String[] StringCases = {"string", "1234"};

		for(int i=0; i<modes.length; i++)
		{
			for(int j=0; j<StringCases.length; j++)
			{
				for(int k=0; k<StringCases.length; k++)
				{
					init(modes[i]);
					loadAddress_FromType("01029591783");
					typeTitle(StringCases[j]);
					typeMessage(StringCases[k]);
					//send();
				}
			}
		}
		
	}
}
