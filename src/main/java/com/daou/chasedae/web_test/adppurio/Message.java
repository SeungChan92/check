package com.daou.chasedae.web_test.adppurio;

import static org.junit.Assert.*;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import com.daou.chasedae.web_test.common.Category;
import com.daou.chasedae.web_test.common.Fail;
import com.daou.chasedae.web_test.common.Parameter;
import com.daou.chasedae.web_test.common.Tool;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Message extends Category {

	public Message(WebDriver driver, String baseUrl, Tool tool, ExtentTest logger) {
		super("Message", driver, baseUrl, tool, logger);
	}

	public void init(String mode) throws Fail {
		try {
			driver.get(baseUrl + "/");
			driver.findElement(By.linkText("문자")).click();
			driver.findElement(By.linkText(mode)).click();

			log.info("message - mode : " + mode);
			
			logger.log(LogStatus.PASS, "init");
		} catch (Exception e) {
			System.out.println(ExceptionUtils.getStackTrace(e));
			logger.log(LogStatus.ERROR
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
			driver.findElement(By.id("sendMainDtoSubject")).clear();
			driver.findElement(By.id("sendMainDtoSubject")).sendKeys(title);
			
			logger.log(LogStatus.PASS, "typeTitle");
		} catch (Exception e) {
			logger.log(LogStatus.ERROR
					, "typeTitle<br>"
					+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
		}
	}

	public void typeMessage(String message) throws Fail {
		try {
			driver.findElement(By.id("sendMessageDtoMessage")).clear();
			driver.findElement(By.id("sendMessageDtoMessage")).sendKeys(message);
			
			logger.log(LogStatus.PASS, "typeMessage");
		} catch (Exception e) {
			System.out.println(e.toString());
			Parameter[] parameters = new Parameter[1];
			parameters[0] = new Parameter("message", message);
			throw new Fail(category, "typeMessage(String message)", parameters, e);
		}
	}

	public void send() throws Fail {
		try {
			driver.findElement(By.id("textReceiverInput")).clear();
			driver.findElement(By.id("sendMainDtoSendNumber")).click();
			driver.findElement(By.name("chkSendNumber")).click();
			driver.findElement(By.cssSelector("p.ph_num")).click();
			driver.findElement(By.linkText("저장 및 적용")).click();
			tool.waitForAlert();
			tool.closeAlertAndGetItsText();
			//assertTrue(tool.closeAlertAndGetItsText().matches("^\\[01029591783\\]를 기본번호로 저장 및 적용하시겠습니까[\\s\\S]$"));
			tool.waitForAlert();
			assertEquals("저장되었습니다.", tool.closeAlertAndGetItsText());
			driver.findElement(By.id("btnSend")).click();
			tool.waitForAlert();
			tool.closeAlertAndGetItsText();
			//assertEquals("잔액이 부족합니다.", tool.closeAlertAndGetItsText());
			
			logger.log(LogStatus.PASS, "send");
		} catch (Exception e) {
			logger.log(LogStatus.ERROR
					, "send<br>"
					+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
//			Parameter[] parameters = new Parameter[0];
//			throw new Fail(category, "send()", parameters, e);
		}
	}

	public void loadAddress_FromGroup(String group_name) throws Fail {
		try {
			driver.get(baseUrl + "/send/standard/sms");
			driver.findElement(By.id("btnSendReceiverAddress")).click();
			tool.goTo_PopUp();
			driver.findElement(By.xpath("(//a[text()='" + group_name + "']/../../..//input[@type='checkbox'])")).click();
			driver.findElement(By.id("btnSendReceiverAddressSubmit")).click();
			tool.waitForAlert();
			Thread.sleep(5000);
			tool.closeAlertAndGetItsText();
			//assertTrue(tool.closeAlertAndGetItsText().matches("^선택한 1건을 발송창에 추가 하시겠습니까[\\s\\S]$"));
			driver.findElement(By.id("btnSendReceiverClose")).click();
			tool.goTo_main();
			
			logger.log(LogStatus.PASS, "loadAddress_FromGroup");
		} catch (Exception e) {
			System.out.println(ExceptionUtils.getStackTrace(e));
			logger.log(LogStatus.ERROR
					, "loadAddress_FromGroup<br>"
					+ "<pre>" + ExceptionUtils.getStackTrace(e) + "</pre>");
//			Parameter[] parameters = new Parameter[1];
//			parameters[0] = new Parameter("group_name", group_name);
//			throw new Fail(category, "loadAddress_FromGroup(String group_name)", parameters, e);
		}
	}

	public void loadAddress_FromType(String sendNumber) throws Fail {
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
			tool.waitForAlert();
			tool.closeAlertAndGetItsText();
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

	public void reserve(String day, String hour, String minute) throws Fail {
		try {
			driver.get(baseUrl + "/send/standard/lms");
			driver.findElement(By.id("sendStatusReserve")).click();
			driver.findElement(By.linkText(day)).click();
			new Select(driver.findElement(By.cssSelector("select.hour"))).selectByVisibleText(hour + "시");
			new Select(driver.findElement(By.cssSelector("select.minute"))).selectByVisibleText(minute + "분");
		} catch (Exception e) {
			Parameter[] parameters = new Parameter[3];
			parameters[0] = new Parameter("day", day);
			parameters[1] = new Parameter("hour", hour);
			parameters[2] = new Parameter("minute", minute);
			throw new Fail(category, "reserve(String day, String hour, String minute)", parameters, e);
		}
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
			tool.waitForAlert();
			assertTrue(tool.closeAlertAndGetItsText().matches("^메시지를 저장하시겠습니까[\\s\\S]$"));
			tool.waitForAlert();
			assertEquals("저장하였습니다.", tool.closeAlertAndGetItsText());
			Thread.sleep(1000);
			
			logger.log(LogStatus.PASS, "saveMessage");
		} catch (Exception e) {
			Parameter[] parameters = new Parameter[0];
			throw new Fail(category, "saveMessage()", parameters, e);
		}
	}
}
