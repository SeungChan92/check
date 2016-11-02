package com.daou.check.service.module;

import org.openqa.selenium.By;

import com.daou.check.dto.Dto;

public class Message extends Module {
	public static void goToPage_message() {
		driver.get(Dto.baseUrl + "/sms/sendView");
	}
	public static void send() {
		System.out.println("send");
	}
	public static void type_title(String title) {
		System.out.println("title : " + title);
		driver.findElement(By.id("sendMainDtoSubject")).clear();
		driver.findElement(By.id("sendMainDtoSubject")).sendKeys(title);
	}
}
