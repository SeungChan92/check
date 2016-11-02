package com.daou.verification.service.module;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tool extends Module {
	public static WebDriverWait wait = new WebDriverWait(driver, 10);
	private static String mainWindowHandle = null;
	private static boolean acceptNextAlert = true;

	public static void waitFor_alert() throws InterruptedException
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
				Thread.sleep(1000);
				continue;
			}
		}
	}
	public static void waitFor_allDone() throws InterruptedException {
		Thread.sleep(500);
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

	public static void click(By linkText) {
		wait.until(ExpectedConditions.elementToBeClickable(linkText));
		driver.findElement(linkText).click();
	}
}