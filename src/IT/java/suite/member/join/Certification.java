package member.join;

import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import infra.Config;
import infra.Tool;
import suite.Suite;

public class Certification extends Suite {
	@BeforeClass
	public static void goToPage_certification() {
		Tool.goToPage("/join/");
		agree_4();
	}
	
	@Test
	public void buttonClick_certify() {
		By button_certify = By.id("kmcRequest");
		By button_kmc = By.xpath("//*[@id='container']/ul/li[4]/a");
		By input_radio_mvno_corp_ktm = By.id("mvno_corp_ktm");
		By button_ok = By.className("btn_ok");
		
		By input_text_userName = By.name("userName");
		By input_text_Birth = By.name("Birth");
		By input_text_No = By.name("No");
		By button_cancel = By.className("btn_cancel");
		
		webDriver.findElement(button_certify).click();
		Tool.goTo_PopUp();
		webDriver.findElement(button_kmc).click();
		webDriver.findElement(input_radio_mvno_corp_ktm).click();
		webDriver.findElement(button_ok).click();
		
		// 정보입력
		webDriver.findElement(input_text_userName).sendKeys(Config.get("name"));
		webDriver.findElement(input_text_Birth).sendKeys(Config.get("birth"));
		webDriver.findElement(input_text_No).sendKeys(Config.get("phone"));
		
		webDriver.findElement(button_cancel).click();
		Tool.goTo_main();
	}
	
	private static void agree_4() {
		By input_checkbox_agreementCheck = By.id("agreementCheck");
		By input_checkbox_privacyCheck = By.id("privacyCheck");
		By input_checkbox_trustCheck = By.id("trustCheck");
		By input_checkbox_marketingCheck = By.id("marketingCheck");
		
		Suite.webDriver.findElement(input_checkbox_agreementCheck).click();
		Suite.webDriver.findElement(input_checkbox_privacyCheck).click();
		Suite.webDriver.findElement(input_checkbox_trustCheck).click();
		Suite.webDriver.findElement(input_checkbox_marketingCheck).click();
		click_button_agreementBtn();
		Tool.assertEquals_currentUrl("/join/auth");
	}
	private static void click_button_agreementBtn() {
		By button_agreementBtn = By.id("agreementBtn");
		
		Suite.webDriver.findElement(button_agreementBtn).click();
	}	
}