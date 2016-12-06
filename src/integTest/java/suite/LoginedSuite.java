package suite;

import org.junit.BeforeClass;
import org.openqa.selenium.support.PageFactory;

import infra.webdriverextension.Tool;
import page.section.LoginSection;

public abstract class LoginedSuite extends Suite {
	
	@BeforeClass
	public static void login () {
		
		LoginSection loginSection = null;
		
		Tool.goToPage("/");
		loginSection = PageFactory.initElements(driver, LoginSection.class);
		loginSection.login();
	}
}
