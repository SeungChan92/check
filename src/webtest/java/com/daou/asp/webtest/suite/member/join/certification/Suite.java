package com.daou.asp.webtest.suite.member.join.certification;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import com.daou.asp.webtest.infra.Config;
import com.daou.asp.webtest.infra.webdriverextension.Tool;

public class Suite extends com.daou.asp.webtest.suite.Suite {
	
	private com.daou.asp.webtest.page.join.Page firstPage = null;
	private com.daou.asp.webtest.page.join.auth.Page secondPage = null;
	
	@Before
	public void goToFirstPage() {
		Tool.goToPage("/join/");
		this.firstPage = PageFactory.initElements(driver
				, com.daou.asp.webtest.page.join.Page.class);
		this.firstPage.check_agreementCheck();
		this.firstPage.check_privacyCheck();
		this.firstPage.check_trustCheck();
		this.firstPage.check_marketingCheck();
		this.firstPage.click_agreementBtn_pageMove();
		this.secondPage = PageFactory.initElements(driver
				, com.daou.asp.webtest.page.join.auth.Page.class);
	}
	
	@Test
	public void certify() {
		By button_certify = By.id("kmcRequest");
		By button_kmc = By.xpath("//*[@id='container']/ul/li[4]/a");
		By input_radio_mvno_corp_ktm = By.id("mvno_corp_ktm");
		By button_ok = By.className("btn_ok");
		
		By input_text_userName = By.name("userName");
		By input_text_Birth = By.name("Birth");
		By input_text_No = By.name("No");
		By button_cancel = By.className("btn_cancel");
		
		driver.findElement(button_certify).click();
		Tool.goTo_popUp();
		driver.findElement(button_kmc).click();
		driver.findElement(input_radio_mvno_corp_ktm).click();
		driver.findElement(button_ok).click();
		
		// 정보입력
		driver.findElement(input_text_userName).sendKeys(Config.get_fromService("name"));
		driver.findElement(input_text_Birth).sendKeys(Config.get_fromService("birth"));
		driver.findElement(input_text_No).sendKeys(Config.get_fromService("phone"));
		
		driver.findElement(button_cancel).click();
		Tool.goTo_main();
	}	
}