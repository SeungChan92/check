package suite.message.sms;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.Config;
import infra.Tool;
import page.LoginSection;

public class Suite extends suite.LoginedSuite {
	
	private page.sms.sendView.Page page_sms_sendView = null;
	
	@Before
	public void goToPage_sms() {
		Tool.goToPage("/sms/sendView");
		this.page_sms_sendView = PageFactory.initElements(driver, page.sms.sendView.Page.class);
	}
	
	@Test
	public void typeTitle_moreThan_30 () {
		String string_31 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		this.page_sms_sendView.type_sendMainDtoSubject(string_31);
		
		Tool.waitFor_alert();
		Assert.assertEquals("제목은 30자이내로 작성해 주세요.", Tool.closeAlert_andGetItsText());		
	}
	@Test
	public void send_instantly() {
		
		this.page_sms_sendView.type_sendMainDtoSubject(Config.get_fromService("title"));
		this.page_sms_sendView.type_sendMessageDtoMessage(Config.get_fromService("message"));
		this.page_sms_sendView.type_textReceiverInput(Config.get_fromService("receiver"));
		this.page_sms_sendView.click_btnReceiverAdd();
		this.page_sms_sendView.click_btnSend();
		
		Tool.waitFor_alert();
		Assert.assertEquals("발송하였습니다.", Tool.closeAlert_andGetItsText());
	}
	@Test
	public void add_receiver_fromGroup() {
		
		page.send.receiver.popup.address.groups.Page page_popup = null;
		
		this.page_sms_sendView.click_btnSendReceiverAddress();
		
		Tool.goTo_PopUp();
		
		page_popup = PageFactory.initElements(driver, page.send.receiver.popup.address.groups.Page.class);
		page_popup.click_checkbox();
		page_popup.click_add();
		Tool.closeAlert_andGetItsText();
		page_popup.click_close();
		
		Tool.goTo_main();
		
		this.page_sms_sendView = PageFactory.initElements(driver, page.sms.sendView.Page.class);
		this.page_sms_sendView.li_group.isDisplayed();
	}
}
