package com.daou.verification.service.module;

import org.openqa.selenium.By;

import com.daou.verification.config.Config;

public class Message extends Module {
	public static void goToPage_message() {
		driver.get(Config.get("baseUrl") + "/sms/sendView");
	}
	
	public static void typeTitle(String title) {
		By input_text_title = By.id("sendMainDtoSubject");
		
		driver.findElement(input_text_title).clear();
		driver.findElement(input_text_title).sendKeys(title);
	}
	public static void typeMessage(String message) {
		By textarea_message = By.id("sendMessageDtoMessage");
		
		driver.findElement(textarea_message).clear();
		driver.findElement(textarea_message).sendKeys(message);
	}

	public static void addReceiver_fromType(String sendNumber) throws InterruptedException {
		By textarea_receiver = By.id("textReceiverInput");
		By button_addReceiver = By.id("btnReceiverAdd");
		
		driver.findElement(textarea_receiver).click();
		driver.findElement(textarea_receiver).sendKeys(sendNumber);
		driver.findElement(button_addReceiver).click();
		//Tool.waitFor_alert();
		//Tool.closeAlert_andGetItsText();
	}
	
	public static void clickSendButton() throws InterruptedException {
		By a_btnSend = By.id("btnSend");
		
		driver.findElement(a_btnSend).click();
		Tool.waitFor_alert();
		System.out.println("ALERT MESSAGE : " + Tool.closeAlert_andGetItsText());
	}
}
