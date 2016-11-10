package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginSection {
	@FindBy(name = "userId")
	private WebElement input_text_userId;

	public void type_userId(String userId) {
		input_text_userId.clear();
		input_text_userId.sendKeys(userId);
    }
}
