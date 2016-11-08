package member.join;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import infra.Tool;
import suite.Suite;

public class Agreement extends Suite {
	
	private By input_checkbox_agreementCheck = By.id("agreementCheck");
	private By input_checkbox_privacyCheck = By.id("privacyCheck");
	private By input_checkbox_trustCheck = By.id("trustCheck");
	private By input_checkbox_marketingCheck = By.id("marketingCheck");
	
	@Before
	public void goToPage_agreement() {
		Tool.goToPage("/join/");
	}
	
	@Test
	public void agree_0() {
		this.click_button_agreementBtn();
		Tool.assertEquals_alert("이용약관에 동의하셔야 합니다.");
	}
	@Test
	public void agree_1() {
		Suite.webDriver.findElement(this.input_checkbox_agreementCheck).click();
		this.click_button_agreementBtn();
		Tool.assertEquals_alert("개인정보 수집 및 이용 항목에 동의하셔야 합니다.");
	}
	@Test
	public void agree_2() {
		Suite.webDriver.findElement(this.input_checkbox_agreementCheck).click();
		Suite.webDriver.findElement(this.input_checkbox_privacyCheck).click();
		this.click_button_agreementBtn();
		Tool.assertEquals_alert("개인정보 취급위탁 항목에 동의하셔야 합니다.");
	}
	@Test
	public void agree_3() {
		Suite.webDriver.findElement(this.input_checkbox_agreementCheck).click();
		Suite.webDriver.findElement(this.input_checkbox_privacyCheck).click();
		Suite.webDriver.findElement(this.input_checkbox_trustCheck).click();
		this.click_button_agreementBtn();
		Tool.assertEquals_currentUrl("/join/auth");
	}
	@Test
	public void agree_4() {
		Suite.webDriver.findElement(this.input_checkbox_agreementCheck).click();
		Suite.webDriver.findElement(this.input_checkbox_privacyCheck).click();
		Suite.webDriver.findElement(this.input_checkbox_trustCheck).click();
		Suite.webDriver.findElement(this.input_checkbox_marketingCheck).click();
		this.click_button_agreementBtn();
		Tool.assertEquals_currentUrl("/join/auth");
	}
	
	private void click_button_agreementBtn() {
		By button_agreementBtn = By.id("agreementBtn");
		
		Suite.webDriver.findElement(button_agreementBtn).click();
	}
}
