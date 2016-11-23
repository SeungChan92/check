package suite.member.join.agreement;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;

public class Suite extends suite.Suite {
	
	private page.join.Page firstPage = null;
	
	@Before
	public void goToPage_agreement() {
		Tool.goToPage("/join/");
		this.firstPage = PageFactory.initElements(driver, page.join.Page.class);
	}

	@Test
	public void agree_0() {
		this.firstPage.click_agreementBtn();
		Tool.assertEquals_alert("이용약관에 동의하셔야 합니다.");
	}
	@Test
	public void agree_1() {
		this.firstPage.check_agreementCheck();
		this.firstPage.click_agreementBtn();
		Tool.assertEquals_alert("개인정보 수집 및 이용 항목에 동의하셔야 합니다.");
	}
	@Test
	public void agree_2() {
		this.firstPage.check_agreementCheck();
		this.firstPage.check_privacyCheck();
		this.firstPage.click_agreementBtn();
		Tool.assertEquals_alert("개인정보 취급위탁 항목에 동의하셔야 합니다.");
	}
	@Test
	public void agree_3() {
		this.firstPage.check_agreementCheck();
		this.firstPage.check_privacyCheck();
		this.firstPage.check_trustCheck();
		this.firstPage.click_agreementBtn_pageMove();
		Tool.assertEquals_currentUrl("/join/auth");
	}
	@Test
	public void agree_4() {
		this.firstPage.check_agreementCheck();
		this.firstPage.check_privacyCheck();
		this.firstPage.check_trustCheck();
		this.firstPage.check_marketingCheck();
		this.firstPage.click_agreementBtn_pageMove();
		Tool.assertEquals_currentUrl("/join/auth");
	}
}
