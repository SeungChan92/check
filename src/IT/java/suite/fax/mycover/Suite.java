package suite.fax.mycover;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;
import page.LoginSection;

public class Suite extends suite.Suite {
	
	private page.fax.mycover.saveView.Page page_fax_mycover_saveView = null;
	
	@BeforeClass
	public static void login () {
		
		LoginSection loginSection = null;
		
		Tool.goToPage("/");
		loginSection = PageFactory.initElements(driver, LoginSection.class);
		loginSection.login();
	}
	
	@Before
	public void goToPage() {
		Tool.goToPage("/fax/mycover/saveView");
		this.page_fax_mycover_saveView = PageFactory.initElements(driver
				, page.fax.mycover.saveView.Page.class);
	}
	
	@Test
	public void register_upperLogo() {
		this.page_fax_mycover_saveView.delete_upperLogo_if_exist();
		this.page_fax_mycover_saveView.select_upperLogo();
		this.page_fax_mycover_saveView.clickButton_save();
	}
}
