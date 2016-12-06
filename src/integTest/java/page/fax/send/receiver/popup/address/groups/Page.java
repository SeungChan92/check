package page.fax.send.receiver.popup.address.groups;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.webdriverextension.Tool;

public class Page extends page.Page {

	public static void goToPage() {
		Tool.goToPage("/fax/send/receiver/popup/address/groups");
	}
	
	public Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "/html/body/div/div[2]/div/div[2]/div[2]/table/tbody/tr[1]/td[1]/input")
	private WebElement checkbox1;
	
	@FindBy(id = "btnSendReceiverAddressSubmit")
	private WebElement button_btnSendReceiverAddressSubmit;
	@FindBy(id = "btnSendReceiverClose")
	private WebElement button_btnSendReceiverClose;
	
	public Page click_checkbox() {
	    this.checkbox1.click();
	    
	    return this;
	}
	public Page click_add() {
	    this.button_btnSendReceiverAddressSubmit.click();
	    
	    return this;
	}
	public void click_cancel() {
	    this.button_btnSendReceiverClose.click();;
	}
}
