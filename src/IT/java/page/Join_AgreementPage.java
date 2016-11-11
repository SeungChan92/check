package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import infra.Config;

public class Join_AgreementPage extends Page {
	
	public Join_AgreementPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id = "agreementCheck")
	private WebElement input_checkbox_agreementCheck;
	@FindBy(id = "privacyCheck")
	private WebElement input_checkbox_privacyCheck;
	@FindBy(id = "trustCheck")
	private WebElement input_checkbox_trustCheck;
	@FindBy(id = "marketingCheck")
	private WebElement input_checkbox_marketingCheck;
	
	@FindBy(id = "agreementBtn")
	private WebElement button_agreementBtn;
	
	public Join_AgreementPage check_agreementCheck() {
		input_checkbox_agreementCheck.click();
		
		return this;
	}
	public Join_AgreementPage check_privacyCheck() {
		input_checkbox_privacyCheck.click();
		
		return this;
	}
	public Join_AgreementPage check_trustCheck() {
		input_checkbox_trustCheck.click();
		
		return this;
	}
	public Join_AgreementPage check_marketingCheck() {
		input_checkbox_marketingCheck.click();
		
		return this;
	}

//	public Join_CertificationPage click_agreementBtn() {
//		button_agreementBtn.click();
//		
//		return new Join_CertificationPage(this.driver);
//	}
//	public Join_AgreementPage click_agreementBtn_expecting_failure() {
//		button_agreementBtn.click();
//		
//		return new Join_AgreementPage(this.driver);
//	}
	public void click_agreementBtn() {
		button_agreementBtn.click();
	}
	public void click_agreementBtn_expecting_failure() {
		button_agreementBtn.click();
	}
}
