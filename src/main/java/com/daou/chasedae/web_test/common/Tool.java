package com.daou.chasedae.web_test.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.daou.chasedae.dataStructure.InputTag;
import com.daou.chasedae.dataStructure.Snapshot;

public class Tool {

	private WebDriver driver;
	public WebDriverWait wait;
	private static String mainWindowHandle;
	private boolean acceptNextAlert = true;
	private String baseUrl;

	private JSONObject snapshot;
	private String snapshot_url;

	private Logger logger = LogManager.getRootLogger();

	public Tool(WebDriver driver) {
		this.driver = driver;

		// init WebDriverWait
		wait = new WebDriverWait(driver, 10);
	}
	public Tool(WebDriver driver, String baseUrl) {
		this.driver = driver;
		this.baseUrl = baseUrl;

		// init WebDriverWait
		wait = new WebDriverWait(driver, 10);
	}

	public void waitFor_alert() throws InterruptedException
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
	public void waitFor_allDone() throws InterruptedException {
		Thread.sleep(500);
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
	public void closeAlert_andSaveItsText() {
		logger.debug("Tool - closeAlert_andSaveItsText() : start");

		String message = closeAlert_andGetItsText();

		save_alertMessage(message);
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
	public void snapshot()
	{
		JSONArray formTags = new JSONArray();
		List<WebElement> web_formTags = driver.findElements(By.tagName("form"));

		snapshot = new JSONObject();
		snapshot.put("formTags", formTags);
		build_formTags(formTags, web_formTags);
		
		snapshot_url = driver.getCurrentUrl();
	}
	private void build_formTags(JSONArray formTags, List<WebElement> web_formTags) {
		JSONObject formTag = null;
		WebElement web_formTag = null;

		for(Iterator<WebElement> it_formTag = web_formTags.iterator();it_formTag.hasNext();)
		{
			web_formTag = it_formTag.next();

			formTag = new JSONObject();
			formTags.add(formTag);
			build_formTag(formTag, web_formTag);
		}
	}
	private void build_formTag(JSONObject formTag, WebElement web_formTag) {
		JSONObject attributes = new JSONObject();
		JSONArray inputTags = new JSONArray();
		List<WebElement> web_inputTags = web_formTag.findElements(By.tagName("input"));
		List<WebElement> web_textareaTags = web_formTag.findElements(By.tagName("textarea"));
		web_inputTags.addAll(web_textareaTags);

		// add children to parent
		formTag.put("attributes", attributes);
		formTag.put("inputTags", inputTags);

		// build children
		build_formTag_attribute(attributes, web_formTag);
		build_inputTags(inputTags, web_inputTags);		
	}
	private void build_formTag_attribute(JSONObject attributes, WebElement web_formTag) {
		attributes.put("id", web_formTag.getAttribute("id"));
		attributes.put("name", web_formTag.getAttribute("name"));
	}
	private void build_inputTags(JSONArray inputTags, List<WebElement> web_inputTags) {
		JSONObject inputTag = null;
		WebElement web_inputTag = null;

		for(Iterator<WebElement> it_inputTag = web_inputTags.iterator(); it_inputTag.hasNext();)
		{
			web_inputTag = it_inputTag.next();
			inputTag = new JSONObject();

			inputTags.add(inputTag);
			build_inputTag(inputTag, web_inputTag);
		}
	}
	private void build_inputTag(JSONObject inputTag, WebElement web_inputTag) {
		JSONObject attributes = new JSONObject();

		inputTag.put("attributes", attributes);
		build_inputTag_attribute(attributes, web_inputTag);
	}
	private void build_inputTag_attribute(JSONObject attributes, WebElement web_inputTag) {
		attributes.put("id", web_inputTag.getAttribute("id"));
		attributes.put("name", web_inputTag.getAttribute("name"));
		attributes.put("value", web_inputTag.getAttribute("value"));
		attributes.put("type", web_inputTag.getAttribute("type"));
	}

	public void save_alertMessage(String message) {
		logger.debug("Tool - save_alertMessage() : start");
		logger.debug("Tool - save_alertMessage() - snapshot : " + snapshot);

		Data.add_alertMessage(snapshot_url, baseUrl, snapshot, message);
	}
	
	// observe
	public void observe_click(By button_by) throws InterruptedException {
		snapshot();
		driver.findElement(button_by).click();
		waitFor_alert();
		closeAlert_andSaveItsText();
	}
	
	public void selectByValue(By select, String value) {
		WebElement element_select = driver.findElement(select);
	    Select dropDown = new Select(element_select);
	    dropDown.selectByValue(value);
	}
}
