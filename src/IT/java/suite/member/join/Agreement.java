package suite.member.join;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;
import page.Join_AgreementPage;
import suite.Suite;

public class Agreement extends Suite {
	
	private Join_AgreementPage join_agreementPage = null;
	//private Join_AgreementPage join_agreementPage = PageFactory.initElements(Suite.driver, Join_AgreementPage.class);
	
	@Before
	public void goToPage_agreement() {
		Tool.goToPage("/join/");
		this.join_agreementPage = PageFactory.initElements(Suite.driver, Join_AgreementPage.class);
	}

	@Test
	public void agree_0() {
		this.join_agreementPage.click_agreementBtn();
		Tool.assertEquals_alert("이용약관에 동의하셔야 합니다.");
	}
	@Test
	public void agree_1() {
		this.join_agreementPage.check_agreementCheck();
		this.join_agreementPage.click_agreementBtn();
		Tool.assertEquals_alert("개인정보 수집 및 이용 항목에 동의하셔야 합니다.");
	}
	@Test
	public void agree_2() {
		this.join_agreementPage.check_agreementCheck();
		this.join_agreementPage.check_privacyCheck();
		this.join_agreementPage.click_agreementBtn();
		Tool.assertEquals_alert("개인정보 취급위탁 항목에 동의하셔야 합니다.");
	}
	@Test
	public void agree_3() {
		this.join_agreementPage.check_agreementCheck();
		this.join_agreementPage.check_privacyCheck();
		this.join_agreementPage.check_trustCheck();
		this.join_agreementPage.click_agreementBtn();
		Tool.assertEquals_currentUrl("/join/auth");
	}
	@Test
	public void agree_4() {
		this.join_agreementPage.check_agreementCheck();
		this.join_agreementPage.check_privacyCheck();
		this.join_agreementPage.check_trustCheck();
		this.join_agreementPage.check_marketingCheck();
		this.join_agreementPage.click_agreementBtn();
		Tool.assertEquals_currentUrl("/join/auth");
	}
}
