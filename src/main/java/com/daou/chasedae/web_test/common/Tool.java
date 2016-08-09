package com.daou.chasedae.web_test.common;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tool {

	private WebDriver driver;
	public WebDriverWait wait;
	private static String mainWindowHandle;
	private boolean acceptNextAlert = true;

	public Tool(WebDriver driver) {
		this.driver = driver;

		// init WebDriverWait
		wait = new WebDriverWait(driver, 10);
	}

	public void waitForAlert() throws InterruptedException
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

	public void goTo_PopUp() {
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
	
	public void goTo_main() {
		driver.switchTo().window(mainWindowHandle);
		System.out.println("focus : main");
	}

	public String closeAlertAndGetItsText() {
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

	public void click(By linkText) {
		wait.until(ExpectedConditions.elementToBeClickable(linkText));
		driver.findElement(linkText).click();
	}
	
	public void rememberMainWindow() {
		mainWindowHandle = driver.getWindowHandle();
	}
}
