package suite.member.login;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import infra.Common;
import infra.Config;
import infra.Tool;
import suite.Suite;

public class Wrong_3Times extends Suite {
	
	///*
	@BeforeClass
	public static void goToPage_wrong3times() {
		Tool.goToPage("/login?fail=who");
	}
	
	@Test
	public void id_blank_pw_blank() {
		Common.login("", "");
		Tool.waitFor_alert();
		assertEquals("id를 입력해 주세요.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void id_right_pw_blank() {
		Common.login(Config.get("id"), "");
		Tool.waitFor_alert();
		assertEquals("비밀번호를 입력해 주세요.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void id_right_pw_right() {
		Common.login();
		Tool.waitFor_alert();
		assertEquals("자동입력 방지문자를 입력해 주세요", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void id_right_pw_right_captcha_short() {
		this.login(Config.get("id"), Config.get("pw"), "a");
		Tool.waitFor_alert();
		assertEquals("자동입력 방지문자는 최소 6글자 입니다.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void refresh_captcha() {
		By button_funfunBtnResetCaptcha = By.id("funfunBtnResetCaptcha");
		By img_captimg = By.xpath("//*[@id='captcha']/img");
		
		String img_captimg_src_first = null;
		String img_captimg_src_second = null;
		
		img_captimg_src_first = driver.findElement(img_captimg).getAttribute("src");
		driver.findElement(button_funfunBtnResetCaptcha).click();
		img_captimg_src_second = driver.findElement(img_captimg).getAttribute("src");
		
		Assert.assertFalse(null, (img_captimg_src_first.equals(img_captimg_src_second)));
	}
	
	private void login(String id, String pw, String captcha) {
		By input_text_userId = By.id("userId");
		By input_password_userPwd = By.id("userPwd");
		By input_text_userCaptcha = By.id("userCaptcha");

		By button_login = By.id("funfunBtnLogin");

		// [입력] userId
		driver.findElement(input_text_userId).clear();
		driver.findElement(input_text_userId).sendKeys(id);

		// [입력] password
		driver.findElement(input_password_userPwd).clear();
		driver.findElement(input_password_userPwd).sendKeys(pw);
		
		// [입력] 자동입력 방지문자
		driver.findElement(input_text_userCaptcha).clear();
		driver.findElement(input_text_userCaptcha).sendKeys(captcha);

		// [클릭] login button
		driver.findElement(button_login).click();
	}
	//*/
}
