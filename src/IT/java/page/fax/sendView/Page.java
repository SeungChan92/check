package page.fax.sendView;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.Config;
import infra.Tool;

public class Page extends page.Page {

	public static void goTo_page() {
		Tool.goToPage("/fax/sendView");
	}

	public Page(WebDriver driver) {
		super(driver);
	}

	//Receiver
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

	public boolean inputs_are_cleared() {
		boolean inputs_are_cleared = false;

		if (this.input_textReceiverInput.getText().isEmpty() 
				&& this.input_textReceiverName.getText().isEmpty())
		{
			inputs_are_cleared = true;
		}

		return inputs_are_cleared;
	}
	public boolean receiver_is_added() {
		boolean type_receiver_is_added = false;

		if (Tool.check_if_exist(By.name("member")))
		{
			type_receiver_is_added = true;
		}

		return type_receiver_is_added;
	}
}
