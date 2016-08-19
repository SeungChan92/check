package com.daou.chasedae.web_test.common;

import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	private static final String newLine = "<br>";
	
	private static ExtentReports extentReports;
	
	private static Logger logger = LogManager.getRootLogger();

	public static void writeReport() {

		extentReports = new ExtentReports("logs/[test_result]_adppurio.html", true);
		ExtentTest extentTest_site = extentReports.startTest("adppurio");

		append_pages(extentTest_site, Data.pages);

		extentReports.endTest(extentTest_site);
		extentReports.flush();
		extentReports.close();
	}

	private static void append_pages(ExtentTest extentTest_site, JSONArray pages) {

		if (pages == null) return;

		// make child test for every page
		for(Iterator<Object> it_page = Data.pages.iterator();it_page.hasNext();)
		{
			JSONObject page = (JSONObject) it_page.next();
			JSONArray alertMessages = (JSONArray) page.get("alertMessages");
			ExtentTest extentTest_page = extentReports.startTest(page.get("path").toString());

			extentTest_site.appendChild(extentTest_page);
			append_alertMessages(extentTest_page, alertMessages);
		}
	}
	private static void append_formTags(ExtentTest extentTest_page, JSONArray formTags) {

		if (formTags == null) return;

		for(Iterator<Object> it_formTag = formTags.iterator();it_formTag.hasNext();)
		{
			JSONObject formTag = (JSONObject) it_formTag.next();
			JSONArray alertMessages = (JSONArray) formTag.get("alertMessages");
			ExtentTest extentTest_formTag = extentReports.startTest(build_testTitle_formTag(formTag));

			append_alertMessages(extentTest_formTag, alertMessages);

			extentTest_page.appendChild(extentTest_formTag);
		}
	}
	private static void append_alertMessages(ExtentTest extentTest_page, JSONArray alertMessages) {
		if (alertMessages == null) return;

		for(Iterator<Object> it_alertMessage = alertMessages.iterator();it_alertMessage.hasNext();)
		{
			JSONObject alertMessage = (JSONObject) it_alertMessage.next();

			extentTest_page.log(LogStatus.INFO, build_log_alertMessage(alertMessage));
		}
	}

	private static String build_testTitle_formTag(JSONObject formTag) {
		String testTitle_formTag = "";
		JSONObject attributes = (JSONObject) formTag.get("attributes");

		testTitle_formTag += "formTag ( ";
		testTitle_formTag += "id : " + attributes.get("id");
		testTitle_formTag += ", name : " + attributes.get("name");
		testTitle_formTag += " )";

		return testTitle_formTag;
	}
	private static String build_log_alertMessage(JSONObject alertMessage) {
		String log = "";

		String message = (String) alertMessage.get("message");
		JSONObject snapshot = (JSONObject) alertMessage.get("snapshot");
		JSONArray formTags = (JSONArray) snapshot.get("formTags");
		JSONObject formTag = null;
		JSONObject attributes = null;
		JSONArray inputTags = null;

		log += "alertMessage : " + message + newLine + newLine;

		// append every formTags info
		for(Iterator<Object> it_formTag = formTags.iterator();it_formTag.hasNext();)
		{
			formTag = (JSONObject) it_formTag.next();
			attributes = (JSONObject) formTag.get("attributes");
			inputTags = (JSONArray) formTag.get("inputTags");

			logger.debug("Report - build_log_alertMessage - formTag : " + formTag);
			
			log += "<span style='padding-left:10px'>"
					+ "formTag ("
					+ "id : " + attributes.get("id")
					+ ", name : " + attributes.get("name")
					+ " )"
					+ "</span>" + newLine;

			// append every inputTags info
			log += build_log_inputTags(inputTags);
		}

		return log;
	}
	private static String build_log_inputTags(JSONArray inputTags) {
		String log = "";
		
		JSONObject inputTag = null;
		JSONObject attributes = null; 

		for(Iterator<Object> it_inputTag = inputTags.iterator();it_inputTag.hasNext();)
		{
			inputTag = (JSONObject) it_inputTag.next();
			attributes = (JSONObject) inputTag.get("attributes");

			log += "<span style='padding-left:20px"; 
			if(!attributes.get("type").equals("hidden"))
			{
				log += ";background-color:yellow";
			}
			log += "'>";
			log += "inputTag ( "
					+ "id : " + attributes.get("id")
					+ ", name : " + attributes.get("name")
					+ ", value : " + attributes.get("value")
					+ ", type : " + attributes.get("type")
					+ " )" + newLine;
			log += "</span>";
		}

		return log;
	}
}
