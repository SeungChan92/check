package page.join;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.webdriverextension.Tool;

public class Page extends page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}
	
	public static void goToPage() {
		Tool.goToPage("/join/");
	}
	
	@FindBy(id = "agreementCheck")
	private WebElement checkbox_agreementCheck;
	@FindBy(id = "privacyCheck")
	private WebElement checkbox_privacyCheck;
	@FindBy(id = "trustCheck")
	private WebElement checkbox_trustCheck;
	@FindBy(id = "marketingCheck")
	private WebElement checkbox_marketingCheck;
	
	@FindBy(id = "agreementBtn")
	private WebElement button_agreementBtn;

	public Page check_agreementCheck() {
	    this.checkbox_agreementCheck.click();
	    return this;
	}
	public Page check_privacyCheck() {
	    this.checkbox_privacyCheck.click();
	    return this;
	}
	public Page check_trustCheck() {
	    this.checkbox_trustCheck.click();
	    return this;
	}
	public Page check_marketingCheck() {
	    this.checkbox_marketingCheck.click();
	    return this;
	}

	public Page click_agreementBtn() {
	    this.button_agreementBtn.click();
	    return this;
	}
	public page.join.auth.Page click_agreementBtn_pageMove() {
	    this.button_agreementBtn.click();
	    return new page.join.auth.Page(driver);
	}
}
