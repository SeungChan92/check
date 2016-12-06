package page.fax.popup.document;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.webdriverextension.Tool;

public class Page extends page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}

	public static void goToPage() {
		Tool.goToPage("/fax/popup/document");
	}

	@FindBy(id = "funFaxSend")
	private WebElement a_funFaxSend;
	@FindBy(xpath = "/html/body/div/div[2]/div/div/a[2]")
	private WebElement a_close;

	public Page click_funFaxSend() {

		this.a_funFaxSend.click();

		return this;
	}
	public Page click_close() {

		this.a_close.click();

		return this;
	}
}
