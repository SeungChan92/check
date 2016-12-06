package com.daou.asp.webtest.page.send.receiver.popup.file;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page extends com.daou.asp.webtest.page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "uploadFile")
	private WebElement button_uploadFile;
	
	@FindBy(id = "btnSendReceiverFileSubmit")
	private WebElement button_btnSendReceiverFileSubmit;
	@FindBy(id = "btnSendReceiverClose")
	private WebElement button_btnSendReceiverClose;
}
