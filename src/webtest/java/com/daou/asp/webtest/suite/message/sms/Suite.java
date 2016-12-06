package com.daou.asp.webtest.suite.message.sms;

import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.daou.asp.webtest.infra.Config;
import com.daou.asp.webtest.infra.webdriverextension.Tool;

public class Suite extends com.daou.asp.webtest.suite.LoginedSuite {
	
	private com.daou.asp.webtest.page.sms.sendView.Page firstPage = null;
	
	@Before
	public void goToFirstPage() {
		com.daou.asp.webtest.page.sms.sendView.Page.goToPage();
		this.firstPage = PageFactory.initElements(driver, com.daou.asp.webtest.page.sms.sendView.Page.class);
	}
	
	@Test
	public void typeTitle_moreThan_30 () {
		String string_31 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		this.firstPage.type_sendMainDtoSubject(string_31);
		
		Tool.waitFor_alert();
		Assert.assertEquals("제목은 30자이내로 작성해 주세요.", Tool.closeAlert_andGetItsText());		
	}
	@Test
	public void send_instantly() {
		
		this.firstPage.type_sendMainDtoSubject(Config.get_fromService("title"));
		this.firstPage.type_sendMessageDtoMessage(Config.get_fromService("message"));
		this.firstPage.type_textReceiverInput(Config.get_fromService("receiver"));
		this.firstPage.click_btnReceiverAdd();
		this.firstPage.click_btnSend();
		
		Tool.waitFor_alert();
		Assert.assertEquals("발송하였습니다.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void add_receiver_fromGroup() {
		
		com.daou.asp.webtest.page.send.receiver.popup.address.groups.Page page_popup = null;
		
		this.firstPage.click_btnSendReceiverAddress();
		
		Tool.goTo_popUp();
		
		page_popup = PageFactory.initElements(driver, com.daou.asp.webtest.page.send.receiver.popup.address.groups.Page.class);
		page_popup.click_checkbox();
		page_popup.click_add();
		Tool.closeAlert_andGetItsText();
		page_popup.click_close();
		
		Tool.goTo_main();
		
		this.firstPage = PageFactory.initElements(driver, com.daou.asp.webtest.page.sms.sendView.Page.class);
		this.firstPage.li_group.isDisplayed();
	}
}
