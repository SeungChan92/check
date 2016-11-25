package suite.fax.mycover;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

public class Suite extends suite.LoginedSuite {
	
	private page.fax.mycover.saveView.Page page_fax_mycover_saveView = null;
	
	@Override
	public void goToFirstPage() {
		page.fax.mycover.saveView.Page.goToPage();
		this.page_fax_mycover_saveView = PageFactory.initElements(driver
				, page.fax.mycover.saveView.Page.class);
	}
	
	@Test
	public void register_upperLogo() {
		this.page_fax_mycover_saveView.delete_upperLogo_if_exist();
		this.page_fax_mycover_saveView.select_upperLogo();
		this.page_fax_mycover_saveView.clickButton_save();
		
		Assert.assertTrue(this.page_fax_mycover_saveView.checkIf_deleteUpperLogoButton_exist());
	}
	@Test
	public void register_lowerLogo() {
		this.page_fax_mycover_saveView.delete_lowerLogo_if_exist();
		this.page_fax_mycover_saveView.select_lowerLogo();
		this.page_fax_mycover_saveView.clickButton_save();
		
		Assert.assertTrue(this.page_fax_mycover_saveView.checkIf_deleteLowerLogoButton_exist());
	}
}
