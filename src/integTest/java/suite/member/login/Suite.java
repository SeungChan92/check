package suite.member.login;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.support.PageFactory;

import infra.Common;
import infra.Config;
import infra.Tool;
import page.section.LoginSection;

@RunWith(Parameterized.class)
public class Suite extends suite.Suite{
	
	/*
	 * cases
	 * 1 : login fail and alert
	 * 2 : login fail and goToPage_fail
	 * 3 : login success
	 */
	private int case_number;
	
	private String id;
	private String pw;
	private String alertMessage;
	
    private LoginSection loginSection = null;
	
	@Parameters(name = "id:{1}, pw:{2}")
	public static Collection<Object[]> data() {
		Config.init();
        
		return Arrays.asList(new Object[][] {
                 { 1, "", "", "id를 입력해 주세요." }, 
                 { 1, Config.get_fromService("id"), "", "비밀번호를 입력해 주세요." }, 
                 { 2, Config.get_fromService("id"), "a", "" },
                 { 3, Config.get_fromService("id"), Config.get_fromService("pw"), "" }
           });
    }
	public Suite(int case_number, String id, String pw, String alertMessage) {
		this.case_number = case_number;
		this.id = id;
		this.pw = pw;
		this.alertMessage = alertMessage;
    }
	
	@Before
	public void goToFirstPage() {
		Common.goToPage_home();
		loginSection = PageFactory.initElements(driver, LoginSection.class);
	}
	
	@Test
	public void login() {
		loginSection.login(this.id, this.pw);
		Tool.waitFor_alert();
		switch(this.case_number)
		{
		case 1:
			assertEquals(this.alertMessage, Tool.closeAlert_andGetItsText());
			break;
		case 2:
			assertEquals(Config.get_fromService("baseUrl")+"/login/?fail", Suite.driver.getCurrentUrl());
//			assertEquals("https://www-sta.adppurio.com:14051/login/?fail", driver.getCurrentUrl());
			break;
		case 3:
			Common.logout();
			break;
		}
	}
	/*
	@Test // id 3회 오입력
	public void id_wrong_3times() {
		for (int i=0; i<3; i++)
		{
			Common.login("a", "a");
		}
		Tool.assertEquals_currentUrl("/login?fail=who");
	}
	*/
}