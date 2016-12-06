package com.daou.asp.webtest.suite.fax.send.document.popup;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

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
	public void selectDocument() {
		
		com.daou.asp.webtest.page.fax.popup.document.Page popup = null;
		
		this.firstPage.click_funFaxDocument();
	    
	    Tool.goTo_popUp();
	    
	    popup = PageFactory.initElements(driver
	    		, com.daou.asp.webtest.page.fax.popup.document.Page.class);
	    popup
	    		.click_faxDocumentNo()
	    		.click_add()
	    		.click_close();
	    
	    Tool.goTo_main();
	    
	    this.firstPage.li_file.click(); //Assert
	}
	@Test
	public void closePopup() {
		
		com.daou.asp.webtest.page.fax.popup.document.Page popup = null;
		
		this.firstPage.click_funFaxDocument();
		
		Tool.goTo_popUp();
		
		popup = PageFactory.initElements(driver
				, com.daou.asp.webtest.page.fax.popup.document.Page.class);
		popup.click_close();
		
		
		Tool.goTo_main();
	}
}
