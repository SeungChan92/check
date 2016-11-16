package suite.message;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import infra.Config;
import infra.Tool;
import page.LoginSection;
import suite.Suite;

public class SMS extends Suite {
	
	@BeforeClass
	public static void goToPage_sms () {
		
		LoginSection loginSection = null;
		
		Tool.goToPage("/sms/sendView");
		loginSection = PageFactory.initElements(driver, LoginSection.class);
		loginSection.login();
	}
	
	@Test
	public void typeTitle_moreThan_30 () {
		String string_31 = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
		
		typeTitle(string_31);
		Tool.waitFor_alert();
		Assert.assertEquals("제목은 30자이내로 작성해 주세요.", Tool.closeAlert_andGetItsText());		
	}
	@Test
	public void send_instantly() {
		
		this.typeTitle(Config.get_fromService("title"));
		this.typeMessage(Config.get_fromService("message"));
		this.addReceiver_fromType(Config.get_fromService("receiver"));
		this.clickSendButton();
	}
	
	private void typeTitle(String title) {
		By input_sendMainDtoSubject = By.id("sendMainDtoSubject");
		
		Suite.driver.findElement(input_sendMainDtoSubject).clear();
		Suite.driver.findElement(input_sendMainDtoSubject).sendKeys(title);
	}
	private void typeMessage(String message) {
		By textarea_message = By.id("sendMessageDtoMessage");
		
		Suite.driver.findElement(textarea_message).clear();
		Suite.driver.findElement(textarea_message).sendKeys(message);
	}
	private void addReceiver_fromType(String receiver) {
		By textarea_receiver = By.id("textReceiverInput");
		By button_addReceiver = By.id("btnReceiverAdd");
		
		Suite.driver.findElement(textarea_receiver).click();
		Suite.driver.findElement(textarea_receiver).sendKeys(receiver);
		Suite.driver.findElement(button_addReceiver).click();
	}
	private void clickSendButton() {
		By a_btnSend = By.id("btnSend");
		
		Suite.driver.findElement(a_btnSend).click();
	}
	
}
