package page.sms.sendView;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.webdriverextension.Tool;

public class Page extends page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}
	
	public static void goToPage() {
		Tool.goToPage("/sms/sendView");
	}
	
	@FindBy(id = "sendMainDtoSubject")
	private WebElement input_text_sendMainDtoSubject;
	@FindBy(id = "sendMessageDtoMessage")
	private WebElement input_text_sendMessageDtoMessage;
	
	@FindBy(id = "textReceiverInput")
	private WebElement textarea_textReceiverInput;
	@FindBy(id = "btnReceiverAdd")
	private WebElement button_btnReceiverAdd;
	
	@FindBy(name = "group")
	public WebElement li_group;
	
	@FindBy(id = "btnReceiverLatest")
	private WebElement button_btnReceiverLatest;
	@FindBy(id = "btnReceiverOften")
	private WebElement button_btnReceiverOften;
	
	@FindBy(id = "btnSendReceiverAddress")
	private WebElement button_btnSendReceiverAddress;
	@FindBy(id = "btnSendReceiverFile")
	private WebElement button_btnSendReceiverFile;
	
	@FindBy(id = "btnSend")
	private WebElement button_btnSend;
	
	public Page type_sendMainDtoSubject(String sendMainDtoSubject) {
		Tool.type(this.input_text_sendMainDtoSubject, sendMainDtoSubject);
		
		return this;
	}
	public Page type_sendMessageDtoMessage(String sendMessageDtoMessage) {
		Tool.type(this.input_text_sendMessageDtoMessage, sendMessageDtoMessage);
		
		return this;
	}
	public Page type_textReceiverInput(String textReceiverInput) {
		Tool.type(this.textarea_textReceiverInput, textReceiverInput);
		
		return this;
	}
	
	public Page click_btnReceiverAdd() {
		this.button_btnReceiverAdd.click();
		
		return this;
	}
	public Page click_btnSendReceiverAddress() {
		this.button_btnSendReceiverAddress.click();
		
		return this;
	}
	public void click_btnSend() {
		button_btnSend.click();
	}
}
