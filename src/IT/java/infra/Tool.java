package infra;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tool implements Infra {

	private static WebDriver webDriver;
	public static WebDriverWait wait;
	private static String mainWindowHandle;
	private static boolean acceptNextAlert = true;

	public static void init(WebDriver webDriver) {
		Tool.webDriver = webDriver;

		// init WebDriverWait
		Tool.wait = new WebDriverWait(Tool.webDriver, 10);
	}

	public static void waitFor_alert()
	{
		int i=0;
		while(i++<5)
		{
			try
			{
				webDriver.switchTo().alert();
				//Alert alert = driver.switchTo().alert();
				break;
			}
			catch(NoAlertPresentException e)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				continue;
			}
		}
	}
	public static void waitFor_allDone() throws InterruptedException {
		Thread.sleep(500);
	}
	public static String closeAlert_andGetItsText() {
		try {
			Alert alert = webDriver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	public static void goTo_PopUp() {
		mainWindowHandle = webDriver.getWindowHandle();

		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return (d.getWindowHandles().size() != 1);
			}
		});

		for (String activeHandle : webDriver.getWindowHandles()) {
			if (!activeHandle.equals(mainWindowHandle)) {
				webDriver.switchTo().window(activeHandle);
				System.out.println("focus : pop-up");
			}
		}
	}
	public static void goTo_main() {
		webDriver.switchTo().window(mainWindowHandle);
		System.out.println("focus : main");
	}

	public static void goToPage(String url) {
		webDriver.get(Config.get("baseUrl")+url);
	}
	
	public static void click(By linkText) {
		wait.until(ExpectedConditions.elementToBeClickable(linkText));
		webDriver.findElement(linkText).click();
	}
	public static void selectByValue(By select, String value) {
		WebElement element_select = webDriver.findElement(select);
	    Select dropDown = new Select(element_select);
	    dropDown.selectByValue(value);
	}
	
	public static void assertEquals_currentUrl(String url) {
		assertEquals(Config.get("baseUrl")+url, Tool.webDriver.getCurrentUrl());
	}
	public static void assertEquals_alert(String alertMessage) {
		Tool.waitFor_alert();
		assertEquals(alertMessage, Tool.closeAlert_andGetItsText());
	}
}