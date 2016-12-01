package suite.fax.send.document;

import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.Tool;
import suite.LoginedSuite;

public class Suite extends LoginedSuite {

	private page.fax.sendView.Page firstPage = null;
	
	@Override
	public void goToFirstPage() {
		page.fax.sendView.Page.goToPage();
		
		this.firstPage = PageFactory.initElements(driver , page.fax.sendView.Page.class); 
	}

	@Test
	public void openDocumentPopup() {
		this.firstPage.click_funFaxDocument();
		
		Tool.goTo_popUp();
		
		new page.fax.popup.document.Page(driver);
		driver.close();
		
		Tool.goTo_main();
	}
}
