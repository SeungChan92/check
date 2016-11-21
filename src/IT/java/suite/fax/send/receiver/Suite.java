package suite.fax.send.receiver;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;

public class Suite extends suite.LoginedSuite  {

private page.fax.mycover.saveView.Page page_fax_mycover_saveView = null;
	
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
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
