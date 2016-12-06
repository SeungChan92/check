package com.daou.asp.webtest.page.fax.popup.document;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.daou.asp.webtest.infra.webdriverextension.Tool;

public class Page extends com.daou.asp.webtest.page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}

	public static void goToPage() {
		Tool.goToPage("/fax/popup/document");
	}

	@FindBy(name = "faxDocumentNo")
	private WebElement checkbox_faxDocumentNo;
	
	@FindBy(id = "funFaxSend")
	private WebElement a_funFaxSend;
	@FindBy(xpath = "/html/body/div/div[2]/div/div/a[2]")
	private WebElement a_close;

	public Page click_add() {

		this.a_funFaxSend.click();

		return this;
	}
	public Page click_close() {

		this.a_close.click();

		return this;
	}
	public Page click_faxDocumentNo() {
		
		this.checkbox_faxDocumentNo.click();
		
		return this;
	}
}
