package com.daou.asp.webtest.suite.fax.send.receiver;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.daou.asp.webtest.infra.Config;
import com.daou.asp.webtest.infra.webdriverextension.Tool;
import com.daou.asp.webtest.suite.LoginedSuite;

public class Suite extends LoginedSuite {

	private com.daou.asp.webtest.page.fax.sendView.Page firstPage = null;
	
	@Override
	public void goToFirstPage() {
		com.daou.asp.webtest.page.fax.sendView.Page.goToPage();
		this.firstPage = PageFactory.initElements(driver
				, com.daou.asp.webtest.page.fax.sendView.Page.class);
	}

	@Test
	public void addByGroup() {
		com.daou.asp.webtest.page.fax.send.receiver.popup.address.groups.Page secondPage = null;
		
	    this.firstPage.click_address();
	    
	    Tool.goTo_popUp();
	    secondPage = PageFactory.initElements(driver
				, com.daou.asp.webtest.page.fax.send.receiver.popup.address.groups.Page.class);
	    secondPage.click_checkbox();
	    secondPage.click_add();
	    Assert.assertEquals("선택한 1건을 발송창에 추가 하시겠습니까?", Tool.closeAlert_andGetItsText());
	    secondPage.click_cancel();
	    
	    Tool.goTo_main();
	    Assert.assertTrue(this.firstPage.checkIf_receiver_is_added_from_group());
	}
	@Test
	public void addByType_sameNumberTwice() {
	    this.firstPage
	    		.type_faxNumber(Config.get_fromService("faxNumber"))
	    		.click_add()
	    		.type_faxNumber(Config.get_fromService("faxNumber"))
	    		.click_add();
	    Assert.assertEquals(1, this.firstPage.countAddedReceiverLines());
	}
	@Test
	public void addByType_multiFaxNumber() {
		
	    this.firstPage
	    		.type_faxNumber(Config.get_fromService("faxNumber_multi"))
	    		.click_add();
	    
	    Assert.assertTrue(null, this.firstPage.countAddedReceiverLines() > 1);
	}
	@Test
	public void deleteAddedReceiver() {
		
	    this.firstPage
		    .type_faxNumber(Config.get_fromService("faxNumber"))
			.click_add();
	    
	    Assert.assertEquals(1, this.firstPage.countAddedReceiverLines());
	    
	    this.firstPage
			.click_addedReceiver(1)
			.click_delete();
	    
	    Assert.assertEquals(0, this.firstPage.countAddedReceiverLines());
	}
}
