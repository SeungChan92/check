package com.daou.asp.webtest.page.section;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.daou.asp.webtest.infra.Config;

public class LoginSection {
	
	@FindBy(name = "userId")
	private WebElement input_text_userId;
	@FindBy(name = "userPwd")
	private WebElement input_password_userPwd;
	
	@FindBy(id = "funfunBtnLogin")
	private WebElement button_funfunBtnLogin;
	
	public void login() {
		this.login(Config.get_fromService("id"), Config.get_fromService("pw"));
	}
	public void login(String userId, String userPwd) {
		this.type_userId(userId);
		this.type_userPwd(userPwd);
		this.click_funfunBtnLogin();
	}
	public void type_userId(String userId) {
		input_text_userId.clear();
		input_text_userId.sendKeys(userId);
    }
	public void type_userPwd(String userPwd) {
		input_password_userPwd.clear();
		input_password_userPwd.sendKeys(userPwd);
    }
	public void click_funfunBtnLogin() {
		button_funfunBtnLogin.click();
	}
}
