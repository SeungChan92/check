package com.daou.check.service.module;

import org.openqa.selenium.By;

import com.daou.check.dto.Dto;

public class Member extends Module {
	public static void login() {
		By input_text_userId = By.id("userId");
		By input_password_userPwd = By.id("userPwd");
		
		By button_login = By.id("funfunBtnLogin");
		
		// [입력] userId
		driver.findElement(input_text_userId).clear();
		driver.findElement(input_text_userId).sendKeys(Dto.userId);
		
		// [입력] password
		driver.findElement(input_password_userPwd).clear();
		driver.findElement(input_password_userPwd).sendKeys(Dto.password);
		
		// [클릭] login button
		driver.findElement(button_login).click();
	}
}
