package suite.fax.send.document.popup;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;

import infra.webdriverextension.Tool;
import suite.LoginedSuite;

public class Suite extends LoginedSuite {

	private page.fax.sendView.Page firstPage = null;
	
	@Override
	public void goToFirstPage() {
		page.fax.sendView.Page.goToPage();

		this.firstPage = PageFactory.initElements(driver
		        , page.fax.sendView.Page.class);
	}
	
	@Test
	public void selectDocument() {
		
		page.fax.popup.document.Page popup = null;
		
		this.firstPage.click_funFaxDocument();
	    
	    Tool.goTo_popUp();
	    
	    popup = PageFactory.initElements(driver
	    		, page.fax.popup.document.Page.class);
	    popup
	    		.click_faxDocumentNo()
	    		.click_add()
	    		.click_close();
	    
	    Tool.goTo_main();
	    
	    this.firstPage.li_file.click(); //Assert
	}
	@Test
	public void closePopup() {
		
		page.fax.popup.document.Page popup = null;
		
		this.firstPage.click_funFaxDocument();
		
		Tool.goTo_popUp();
		
		popup = PageFactory.initElements(driver
				, page.fax.popup.document.Page.class);
		popup.click_close();
		
		
		Tool.goTo_main();
	}
}
