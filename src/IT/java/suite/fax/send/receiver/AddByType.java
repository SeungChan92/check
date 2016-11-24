package suite.fax.send.receiver;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.support.PageFactory;

import infra.Config;
import infra.Tool;

@RunWith(Parameterized.class)
public class AddByType extends suite.LoginedSuite  {

	/*
	 * cases
	 * 1 : fail and alert
	 * 2 : fail and clear inputs
	 * 3 : success
	 */
	private int case_number;
	
	private String faxNumber;
	private String receiver;
	private String alertMessage;
	
	private page.fax.sendView.Page page_fax_sendView = null;
	
	@Parameters(name = "faxNumber:{1}, receiver:{2}")
	public static Collection<Object[]> data() {
		Config.init();
        
		return Arrays.asList(new Object[][] {
                 { 1, "", "", "팩스번호를 입력해 주세요." }, 
                 { 2, "a", "", "" }, 
                 { 3, Config.get_fromService("faxNumber"), Config.get_fromService("name"), "" }
           });
    }
	public AddByType(int case_number, String faxNumber, String receiver, String alertMessage) {
		this.case_number = case_number;
		this.faxNumber = faxNumber;
		this.receiver = receiver;
		this.alertMessage = alertMessage;
    }
	
	@Override
	public void goToFirstPage() {
		page.fax.sendView.Page.goToPage();
		this.page_fax_sendView = PageFactory.initElements(driver
				, page.fax.sendView.Page.class);
	}
	
	@Test
	public void addReceiver() {
		this.page_fax_sendView.type_faxNumber(this.faxNumber);
		this.page_fax_sendView.type_receiver(this.receiver);
		this.page_fax_sendView.click_add();
		
		switch(this.case_number)
		{
		case 1:
			Tool.waitFor_alert();
			assertEquals(this.alertMessage, Tool.closeAlert_andGetItsText());
			break;
		case 2:
			Assert.assertTrue(this.page_fax_sendView.checkIf_inputs_are_cleared());
			break;
		case 3:
			Assert.assertTrue(this.page_fax_sendView.checkIf_receiver_is_added_from_type());
			break;
		}
	}
}
