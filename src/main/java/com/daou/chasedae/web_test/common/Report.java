package com.daou.chasedae.web_test.common;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	private static ExtentReports extentReports;

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
			JSONArray formTags = (JSONArray) page.get("formTags");
			ExtentTest extentTest_page = extentReports.startTest(page.get("path").toString());

			// make child test for every formTag
			append_formTags(extentTest_page, formTags);

			// make child test for every page (cont.)
			extentTest_site.appendChild(extentTest_page);
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
	private static void append_alertMessages(ExtentTest extentTest_formTag, JSONArray alertMessages) {
		if (alertMessages == null) return;

		for(Iterator<Object> it_alertMessage = alertMessages.iterator();it_alertMessage.hasNext();)
		{
			JSONObject alertMessage = (JSONObject) it_alertMessage.next();
			
			extentTest_formTag.log(LogStatus.INFO, build_log_alertMessage(alertMessage));
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
		JSONArray inputTags = (JSONArray) alertMessage.get("inputTags");
		
		log += "<pre>\n";
		log += "alertMessage : " + alertMessage.get("message") + '\n';
		log += "\tinputTags : " + '\n';
		
		// append every inputTag info
		for(Iterator<Object> it_inputTag = inputTags.iterator();it_inputTag.hasNext();)
		{
			JSONObject inputTag = (JSONObject) it_inputTag.next();
			
			log += "\t\tinputTag ( "
					+ "id : " + inputTag.get("id")
					+ ", name : " + inputTag.get("name")
					+ ", value : " + inputTag.get("value")
					+ " )" + '\n';
		}
		
		log += "</pre>";
		
		return log;
	}
}
