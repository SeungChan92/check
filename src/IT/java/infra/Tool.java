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

	private static WebDriver driver;
	public static WebDriverWait wait;
	private static String mainWindowHandle;
	private static boolean acceptNextAlert = true;

	public static void init(WebDriver driver) {
		Tool.driver = driver;

		// init WebDriverWait
		Tool.wait = new WebDriverWait(Tool.driver, 10);
	}

	public static void waitFor_alert()
	{
		int i=0;
		while(i++<5)
		{
			try
			{
				driver.switchTo().alert();
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
			Alert alert = driver.switchTo().alert();
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
		mainWindowHandle = driver.getWindowHandle();

		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver d) {
				return (d.getWindowHandles().size() != 1);
			}
		});

		for (String activeHandle : driver.getWindowHandles()) {
			if (!activeHandle.equals(mainWindowHandle)) {
				driver.switchTo().window(activeHandle);
				System.out.println("focus : pop-up");
			}
		}
	}
	public static void goTo_main() {
		driver.switchTo().window(mainWindowHandle);
		System.out.println("focus : main");
	}

	public static void goToPage(String url) {
		driver.get(Config.get_fromService("baseUrl")+url);
	}
	
	public static void click(By linkText) {
		wait.until(ExpectedConditions.elementToBeClickable(linkText));
		driver.findElement(linkText).click();
	}
	public static void selectByValue(By select, String value) {
		WebElement element_select = driver.findElement(select);
	    Select dropDown = new Select(element_select);
	    dropDown.selectByValue(value);
	}
	
	public static void assertEquals_currentUrl(String url) {
		//assertEquals(Config.get_fromService("baseUrl")+url, Tool.driver.getCurrentUrl());
	}
	public static void assertEquals_alert(String alertMessage) {
		Tool.waitFor_alert();
		assertEquals(alertMessage, Tool.closeAlert_andGetItsText());
	}

	public static void type(WebElement webElement, String string) {
		webElement.clear();
		webElement.sendKeys(string);
	}

	public static boolean check_if_exist(By by) {
		boolean exist = true;

		if (driver.findElements(by).isEmpty()) {
			exist = false;
		}
		
		return exist;
	}
}