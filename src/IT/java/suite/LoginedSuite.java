package suite;

import org.junit.BeforeClass;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;
import page.LoginSection;

public abstract class LoginedSuite extends Suite {
	
	@BeforeClass
	public static void login () {
		
		LoginSection loginSection = null;
		
		Tool.goToPage("/");
		loginSection = PageFactory.initElements(driver, LoginSection.class);
		loginSection.login();
	}
}
