package page.fax.sendView;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import infra.Config;
import infra.webdriverextension.Tool;

public class Page extends page.Page {

	public static void goToPage() {
		Tool.goToPage("/fax/sendView");
	}

	public Page(WebDriver driver) {
		super(driver);
	}

	// Receiver
	@FindBy(id = "textReceiverInput")
	private WebElement input_textReceiverInput;
	@FindBy(id = "textReceiverName")
	private WebElement input_textReceiverName;

	@FindBy(id = "btnReceiverAdd")
	private WebElement button_btnReceiverAdd;
	@FindBy(id = "funPopAddress")
	private WebElement button_funPopAddress;
	@FindBy(id = "btnReceiverSelectedDelete")
	private WebElement button_btnReceiverSelectedDelete;
	@FindBy(id = "btnReceiverAllDelete")
	private WebElement button_btnReceiverAllDelete;
	
	@FindBy(xpath = "//*[@id='sendReceivers']/li")
	private List<WebElement> li_sendReceivers;

	// Document
	@FindBy(id = "funFaxDocument")
	private WebElement a_funFaxDocument;
	@FindBy(name = "file")
	public WebElement li_file;
	
	public Page type_faxNumber(String faxNumber) {

		this.input_textReceiverInput.sendKeys(faxNumber);

		return this;
	}
	public Page type_receiver(String receiver) {

		this.input_textReceiverName.sendKeys(receiver);

		return this;
	}
	
	public Page click_add() {

		this.button_btnReceiverAdd.click();

		return this;
	}
	public Page click_address() {
		this.button_funPopAddress.click();
		
		return this;
	}
	public Page click_delete() {
		this.button_btnReceiverSelectedDelete.click();
		
		return this;
	}
	public Page click_addedReceiver(int addedReceiver_index) {
		this.li_sendReceivers.get(addedReceiver_index-1).click();

		return this;
	}
	public Page click_funFaxDocument() {
		this.a_funFaxDocument.click();
		
		return this;
	}
	
	public boolean checkIf_inputs_are_cleared() {
		boolean inputs_are_cleared = false;

		if (this.input_textReceiverInput.getText().isEmpty() 
				&& this.input_textReceiverName.getText().isEmpty())
		{
			inputs_are_cleared = true;
		}

		return inputs_are_cleared;
	}
	public boolean checkIf_receiver_is_added_from_type() {
		boolean receiver_is_added_from_type = false;

		if (Tool.checkIf_exist(By.name("member")))
		{
			receiver_is_added_from_type = true;
		}

		return receiver_is_added_from_type;
	}
	public boolean checkIf_receiver_is_added_from_group() {
		boolean receiver_is_added_from_group = false;

		if (Tool.checkIf_exist(By.name("group")))
		{
			receiver_is_added_from_group = true;
		}

		return receiver_is_added_from_group;
	}
	public int countAddedReceiverLines() {
		return this.li_sendReceivers.size();
	}
}
