package suite;

import org.junit.BeforeClass;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;
import page.section.Login;

public abstract class LoginedSuite extends Suite {
	
	@BeforeClass
	public static void login () {
		
		Login loginSection = null;
		
		Tool.goToPage("/");
		loginSection = PageFactory.initElements(driver, Login.class);
		loginSection.login();
	}
}
