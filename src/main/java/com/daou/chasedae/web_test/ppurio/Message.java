package com.daou.chasedae.web_test.ppurio;

import static org.junit.Assert.*;
import org.openqa.selenium.*;

import com.daou.chasedae.web_test.common.Tool;

public class Message {
	private WebDriver driver;
	private String baseUrl;
	private Tool tool;
	
	public Message(WebDriver driver, String baseUrl, Tool tool) {
		this.driver = driver;
		this.baseUrl = baseUrl;
		this.tool = tool;
	}

	public void send_short(String title, String message) throws Exception {
		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("문자")).click();
		driver.findElement(By.linkText("단문")).click();
		driver.findElement(By.id("sendMainDtoSubject")).clear();
		driver.findElement(By.id("sendMainDtoSubject")).sendKeys(title);
		driver.findElement(By.id("sendMessageDtoMessage")).clear();
		driver.findElement(By.id("sendMessageDtoMessage")).sendKeys(message);
		driver.findElement(By.id("textReceiverInput")).clear();
		driver.findElement(By.id("textReceiverInput")).sendKeys("01029591783");
		driver.findElement(By.id("btnReceiverAdd")).click();
		tool.waitForAlert();
		assertEquals("1건의 번호가 추가되었습니다.\n(중복되거나 유효하지않은 번호는 삭제됩니다.)", tool.closeAlertAndGetItsText());
		driver.findElement(By.id("sendMainDtoSendNumber")).click();
		driver.findElement(By.name("chkSendNumber")).click();
		driver.findElement(By.cssSelector("p.ph_num")).click();
		driver.findElement(By.linkText("저장 및 적용")).click();
		tool.waitForAlert();
		assertTrue(tool.closeAlertAndGetItsText().matches("^\\[01029591783\\]를 기본번호로 저장 및 적용하시겠습니까[\\s\\S]$"));
		tool.waitForAlert();
		assertEquals("저장되었습니다.", tool.closeAlertAndGetItsText());
		driver.findElement(By.id("btnSend")).click();
		tool.waitForAlert();
		assertEquals("잔액이 부족합니다.", tool.closeAlertAndGetItsText());
	}
}
