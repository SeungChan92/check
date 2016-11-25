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

	public static void logout() {
		By button_logout = By.className("btn_logout");
		
		driver.findElement(button_logout).click();
	}
}
