package com.daou.asp.webtest.page.send.receiver.popup.address.groups;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Page extends com.daou.asp.webtest.page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "/html/body/div/div[2]/div[1]/div[2]/div[2]/table/tbody/tr[1]/td[1]/input")
	private WebElement checkbox;
	
	@FindBy(id = "btnSendReceiverAddressSubmit")
	private WebElement button_btnSendReceiverAddressSubmit;
	@FindBy(id = "btnSendReceiverClose")
	private WebElement button_btnSendReceiverClose;
	
	public Page click_checkbox() {
		this.checkbox.click();
		
		return this;
	}
	
	public Page click_add() {
		this.button_btnSendReceiverAddressSubmit.click();
		
		return this;
	}
	public Page click_close() {
		this.button_btnSendReceiverClose.click();
		
		return this;
	}
}
