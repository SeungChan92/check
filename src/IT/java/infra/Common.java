package infra;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Common implements Infra {

	private static WebDriver driver = null;
	
	public static void init(WebDriver driver) {
		Common.driver = driver;
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
		driver.findElement(input_text_userId).clear();
		driver.findElement(input_text_userId).sendKeys(id);

		// [입력] password
		driver.findElement(input_password_userPwd).clear();
		driver.findElement(input_password_userPwd).sendKeys(pw);

		// [클릭] login button
		driver.findElement(button_login).click();
	}
	public static void logout() {
		By button_logout = By.className("btn_logout");
		
		driver.findElement(button_logout).click();
	}
}
