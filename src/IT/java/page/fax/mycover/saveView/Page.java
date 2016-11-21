package page.fax.mycover.saveView;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.Config;
import infra.Tool;

public class Page extends page.Page {

	public Page(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "uploadFileTop")
	private WebElement input_file_uploadFileTop;
	
	@FindBy(id = "btnFaxCoverFileSubmit")
	private WebElement button_btnFaxCoverFileSubmit;
	@FindBy(id = "topFileDete")
	private WebElement button_topFileDete;

	public Page select_upperLogo() {
		
		this.input_file_uploadFileTop.sendKeys(Config.get_path("logo"));
		
		return this;
	}
	public Page clickButton_save() {
	
		this.button_btnFaxCoverFileSubmit.click();
		
		return this;
	}
	public Page delete_upperLogo_if_exist() {
		
		if(this.button_topFileDete != null)
		{
			this.button_topFileDete.click();
			Tool.closeAlert_andGetItsText();
		}
		
		return this;
	}
}
