package com.daou.asp.webtest.suite.fax.send.document;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import com.daou.asp.webtest.infra.webdriverextension.Tool;
import com.daou.asp.webtest.suite.LoginedSuite;

public class Suite extends LoginedSuite {

	private com.daou.asp.webtest.page.fax.sendView.Page firstPage = null;
	
	@Override
	public void goToFirstPage() {
		com.daou.asp.webtest.page.fax.sendView.Page.goToPage();
		
		this.firstPage = PageFactory.initElements(driver , com.daou.asp.webtest.page.fax.sendView.Page.class); 
	}

	@Test
	public void openDocumentPopup() {
		this.firstPage.click_funFaxDocument();
		
		Tool.goTo_popUp();
		
		new com.daou.asp.webtest.page.fax.popup.document.Page(driver);
		driver.close();
		
		Tool.goTo_main();
	}
}
