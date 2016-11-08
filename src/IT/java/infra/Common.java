package infra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Common implements Infra {

	private static WebDriver webDriver = null;
	
	public static void init(WebDriver webDriver) {
		Common.webDriver = webDriver;
	}
	
	public static void goToPage_home() {
		Tool.goToPage("");
	}
	
	public static void login() {
		login(Config.get("id"), Config.get("pw"));
	}
	public static void login(String id, String pw) {
		By input_text_userId = By.id("userId");
		By input_password_userPwd = By.id("userPwd");

		By button_login = By.id("funfunBtnLogin");

		// [입력] userId
		webDriver.findElement(input_text_userId).clear();
		webDriver.findElement(input_text_userId).sendKeys(id);

		// [입력] password
		webDriver.findElement(input_password_userPwd).clear();
		webDriver.findElement(input_password_userPwd).sendKeys(pw);

		// [클릭] login button
		webDriver.findElement(button_login).click();
	}
}
