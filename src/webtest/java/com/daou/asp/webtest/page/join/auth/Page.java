package com.daou.asp.webtest.page.join.auth;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.daou.asp.webtest.infra.webdriverextension.Tool;

public class Page extends com.daou.asp.webtest.page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}

	public static void goToPage() {
		Tool.goToPage("/join/auth");
	}
	
	@FindBy(id = "kmcRequest")
	private WebElement button_kmcRequest;

	public Page click_kmcRequest() {
	    this.button_kmcRequest.click();
	    return this;
	}
}
