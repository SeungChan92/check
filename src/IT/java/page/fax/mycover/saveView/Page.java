package page.fax.mycover.saveView;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.Config;
import infra.Tool;

public abstract class Page extends page.Page {

	public static void goTo_page() {
		Tool.goToPage("/fax/mycover/saveView");
	}
	
	public Page(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "uploadFileTop")
	private WebElement input_file_uploadFileTop;
	@FindBy(id = "uploadFileBottom")
	private WebElement input_file_uploadFileBottom;

	@FindBy(id = "btnFaxCoverFileSubmit")
	private WebElement button_btnFaxCoverFileSubmit;
	@FindBy(id = "topFileDete")
	private WebElement button_topFileDete;
	@FindBy(id = "bottomFileDete")
	private WebElement button_bottomFileDete;

	public Page select_upperLogo() {

		this.input_file_uploadFileTop.sendKeys(Config.get_path("logo"));

		return this;
	}
	public Page select_lowerLogo() {

		this.input_file_uploadFileBottom.sendKeys(Config.get_path("logo"));

		return this;
	}

	public Page clickButton_save() {

		this.button_btnFaxCoverFileSubmit.click();

		return this;
	}
	
	public Page delete_upperLogo_if_exist() {

		if(Tool.check_if_exist(By.id("topFileDete")))
		{
			this.button_topFileDete.click();
			Tool.closeAlert_andGetItsText();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return this;
	}
	public Page delete_lowerLogo_if_exist() {

		if(Tool.check_if_exist(By.id("bottomFileDete")))
		{
			this.button_bottomFileDete.click();
			Tool.closeAlert_andGetItsText();
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return this;
	}
}
