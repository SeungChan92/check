package message;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;

import infra.Common;
import infra.Config;
import infra.Tool;
import suite.Suite;

public class SMS extends Suite {
	
	@BeforeClass
	public static void goToPage_sms () {
		Tool.goToPage("/sms/sendView");
		Common.login();
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
		
		this.typeTitle(Config.get("title"));
		this.typeMessage(Config.get("message"));
		this.addReceiver_fromType(Config.get("receiver"));
		this.clickSendButton();
	}
	
	private void typeTitle(String title) {
		By input_sendMainDtoSubject = By.id("sendMainDtoSubject");
		
		Suite.webDriver.findElement(input_sendMainDtoSubject).clear();
		Suite.webDriver.findElement(input_sendMainDtoSubject).sendKeys(title);
	}
	private void typeMessage(String message) {
		By textarea_message = By.id("sendMessageDtoMessage");
		
		Suite.webDriver.findElement(textarea_message).clear();
		Suite.webDriver.findElement(textarea_message).sendKeys(message);
	}
	private void addReceiver_fromType(String receiver) {
		By textarea_receiver = By.id("textReceiverInput");
		By button_addReceiver = By.id("btnReceiverAdd");
		
		Suite.webDriver.findElement(textarea_receiver).click();
		Suite.webDriver.findElement(textarea_receiver).sendKeys(receiver);
		Suite.webDriver.findElement(button_addReceiver).click();
	}
	private void clickSendButton() {
		By a_btnSend = By.id("btnSend");
		
		Suite.webDriver.findElement(a_btnSend).click();
	}
	
}
