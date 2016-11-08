package suite.member.login;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;

import infra.Common;
import infra.Config;
import infra.Tool;
import suite.Suite;

public class Login extends Suite{
	
	///*
	@Test
	public void id_blank_pw_blank() {
		Common.goToPage_home();
		Common.login("", "");
		Tool.waitFor_alert();
		assertEquals("id를 입력해 주세요.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void id_right_pw_blank() {
		Common.goToPage_home();
		Common.login(Config.get("id"), "");
		Tool.waitFor_alert();
		assertEquals("비밀번호를 입력해 주세요.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void id_right_pw_wrong() {
		Common.goToPage_home();
		Common.login(Config.get("id"), "a");
		Tool.waitFor_alert();
		assertEquals(Config.get("baseUrl")+"/login/?fail", this.webDriver.getCurrentUrl());
	}
	@Test
	public void id_right_pw_right() {
		By button_logout = By.className("btn_logout");
		
		Common.goToPage_home();
		Common.login();
		
		// [확인] logout button exist
		webDriver.findElement(button_logout);
	}
	@Test // id 3회 오입력
	public void id_wrong_3times() {
		Common.goToPage_home();
		for (int i=0; i<3; i++)
		{
			Common.login("a", "a");
		}
		Tool.assertEquals_currentUrl("/login?fail=who");
	}
	//*/
}