package com.daou.chasedae.web_test.common;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	public String closeAlert_andGetItsText() {
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
	public void closeAlert_andSaveItsText(By formTag_by) {
		String message = closeAlert_andGetItsText();
		
		save_alertMessage(formTag_by, message);
	}

	public void click(By linkText) {
		wait.until(ExpectedConditions.elementToBeClickable(linkText));
		driver.findElement(linkText).click();
	}
	
	public void rememberMainWindow() {
		mainWindowHandle = driver.getWindowHandle();
	}

	public ArrayList<InputTag> make_inputTagList(WebElement formTag) {
		ArrayList<InputTag> inputTagList = new ArrayList<InputTag>();
		List<WebElement> inputTags = formTag.findElements(By.tagName("input"));
		List<WebElement> textareaTags = formTag.findElements(By.tagName("textarea"));
		
		inputTags.addAll(textareaTags);
		
		for(int i=0; i<inputTags.size(); i++)
		{
			WebElement web_inputTag = inputTags.get(i);
			InputTag inputTag = new InputTag();
			
			inputTag.id = web_inputTag.getAttribute("id");
			inputTag.name = web_inputTag.getAttribute("name");
			inputTag.value = web_inputTag.getAttribute("value");
			inputTag.type = web_inputTag.getAttribute("type");
			
			inputTagList.add(inputTag);
		}
		
		return inputTagList;
	}

	// save at Data
	public void save_alertMessage(By formTag_by, String message) {
		WebElement formTag = driver.findElement(formTag_by);
		String formTag_id = formTag.getAttribute("id");
		String formTag_name = formTag.getAttribute("name");
		
		ArrayList<InputTag> inputTagList = make_inputTagList(formTag);
		
		Data.add_alertMessage(driver.getCurrentUrl(), formTag_id, formTag_name, inputTagList, message);
	}
}
