package com.daou.chasedae.web_test.common;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Data {

	private static Logger logger = LogManager.getRootLogger();
	
	private static JSONObject JSONobject = new JSONObject();
	private static JSONArray pages = null; 

	public Data() { }

	public static void readFile() throws FileNotFoundException, IOException, ParseException
	{
		JSONParser parser = new JSONParser();
		JSONobject = (JSONObject) parser.parse(new FileReader(
				"/data/adppurio.json"));
	}
	public static void writeFile() throws IOException
	{
		String json = "";
		ObjectMapper mapper = null;

		mapper = new ObjectMapper(); // Setup Jackson
		//	    mapper.configure(Feature.INDENT_OUTPUT, true);
		//	    mapper.configure(Feature.SORT_PROPERTIES_ALPHABETICALLY, true);
		ObjectWriter writer = mapper.writer();
		writer = writer.withDefaultPrettyPrinter();

		json = writer.writeValueAsString(JSONobject);

		try (FileWriter file = new FileWriter("/data/adppurio.json")) {
			file.write(json);
			//			file.write(Data.JSONobject.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + Data.JSONobject);
		}
	}

	public static void add_page(String path)
	{
		// if there is no page array
		if(!Data.JSONobject.containsKey("pages"))
		{
			Data.JSONobject.put("pages", new JSONArray());
			pages = (JSONArray) Data.JSONobject.get("pages");
		}

		JSONObject page = new JSONObject();
		page.put("path", path);
		pages.add(page);
	}
	public static void add_formTag(String path, Element formTag) {

		JSONObject page = get_page(path);

		// if there is no formTag array
		if(!page.containsKey("formTags"))
		{
			// add formTag array to this pageObject
			JSONArray formTags = new JSONArray();
			page.put("formTags", formTags);
		}

		JSONArray formTags = (JSONArray) page.get("formTags");

		// add formTag_info
		JSONObject json_formTag = new JSONObject();
		JSONObject attributes = new JSONObject();
		attributes.put("id", formTag.attr("id"));
		attributes.put("name", formTag.attr("name"));
		json_formTag.put("attributes", attributes);
		formTags.add(json_formTag);
	}
	public static void add_inputTags(String path, Element formTag, Elements inputTags) {

		JSONObject page = get_page(path);
		JSONArray formTags = (JSONArray) page.get("formTags");
		JSONObject json_formTag = get_formTag(formTags, formTag);
		JSONArray json_inputTags = null;

		if(!json_formTag.containsKey("inputTags"))
		{
			json_formTag.put("inputTags", new JSONArray());
		}

		json_inputTags = (JSONArray) json_formTag.get("inputTags");

		for(Iterator<Element> it_input = inputTags.iterator();it_input.hasNext();)
		{
			Element inputTag = it_input.next();

			JSONObject json_inputTag = new JSONObject();
			JSONObject attributes = new JSONObject();
			attributes.put("id", inputTag.attr("id"));
			attributes.put("name", inputTag.attr("name"));
			json_inputTag.put("attributes", attributes);
			json_inputTags.add(json_inputTag);
		}
	}
	public static void add_alertMessage(String page_url, String formTag_id, String formTag_name
			, ArrayList<InputTag> inputTagList, String message) {

		String page_path = page_url.substring(page_url.indexOf("com") + 3);
		System.out.println("add_alertMessage - page_path : " + page_path);
		JSONObject page = get_page(page_path);
		JSONObject formTag = get_formTag((JSONArray) page.get("formTags"), formTag_id, formTag_name);
		JSONArray alertMessages = null;

		JSONArray inputTags = build_inputTags(inputTagList);
		JSONObject alertMessage = build_alertMessage(inputTags, message);

		if(!formTag.containsKey("alertMessages"))
		{
			formTag.put("alertMessages", new JSONArray());
		}

		alertMessages = (JSONArray) formTag.get("alertMessages");
		alertMessages.add(alertMessage);
		
		System.out.println("Data - add_alertMessage() - alertMessage : " + alertMessage);
		System.out.println("Data - add_alertMessage() - alertMessages : " + alertMessages);
	}

	private static JSONObject build_alertMessage(JSONArray inputTags, String message) {
		JSONObject alertMessage = new JSONObject();

		alertMessage.put("inputTags", inputTags);
		alertMessage.put("message", message);

		return alertMessage;
	}
	private static JSONArray build_inputTags(ArrayList<InputTag> inputTagList) {
		JSONArray inputTags = new JSONArray();

		for(int i=0; i<inputTagList.size(); i++)
		{
			InputTag inputTag = inputTagList.get(i);
			JSONObject json_inputTag = new JSONObject();

			json_inputTag.put("id", inputTag.id);
			json_inputTag.put("name", inputTag.name);
			json_inputTag.put("value", inputTag.value);

			inputTags.add(json_inputTag);
		}

		return inputTags;
	}
	private static JSONObject get_page(String path) {
		int page_index = -1;
		JSONObject page = null;

		// if there is no page array
		if(pages == null)
		{
			pages = (JSONArray) JSONobject.get("pages");
		}

		page_index = get_page_index(path);

		// if there is no such page in page array
		if(page_index == -1)
		{
			// log
			System.out.println("add_formTag - no such page");

			return null;
		}

		page = (JSONObject) pages.get(page_index);

		return page;
	}
	private static JSONObject get_formTag(JSONArray formTags, Element formTag) {

		int formTag_index = -1;
		JSONObject json_formTag = null;

		// if there is no formTag array
		if(formTags == null)
		{
			System.out.println("add_formTag - no formTag array");
			return null;
		}

		formTag_index = get_formTag_index(formTags, formTag);

		// if there is no such formTag in formTag array
		if(formTag_index == -1)
		{
			System.out.println("get_formTag - no such formTag");
			return null;
		}

		json_formTag = (JSONObject) formTags.get(formTag_index);

		return json_formTag;
	}
	private static JSONObject get_formTag(JSONArray formTags, String formTag_id, String formTag_name) {
		
		logger.debug("Data - get_formTag()");
		logger.debug("formTags : " + formTags);
		logger.debug("formTag_id : " + formTag_id);
		logger.debug("formTag_name : " + formTag_name);
		logger.debug("");
		
		int formTag_index = -1;
		JSONObject json_formTag = null;

		formTag_index = get_formTag_index(formTags, formTag_id, formTag_name);

		// if there is no such formTag in formTag array
		if(formTag_index == -1)
		{
			System.out.println("get_formTag - no such formTag");
			return null;
		}

		json_formTag = (JSONObject) formTags.get(formTag_index);

		return json_formTag;
	}

	private static int get_page_index(String path) {
		for(int i=0; i<pages.size(); i++)
		{
//			System.out.println("Data - get_page_index() - json_path : " + (String) (((JSONObject)pages.get(i)).get("path")));
//			System.out.println("Data - get_page_index() - path      : " + path);
			if(((String) (((JSONObject)pages.get(i)).get("path"))).equals(path))
			{
				return i;
			}
		}

		return -1;
	}
	private static int get_formTag_index(JSONArray formTags, Element formTag) {
		for(int i=0; i<formTags.size(); i++)
		{
			JSONObject attributes = (JSONObject) ((JSONObject) formTags.get(i)).get("attributes");
			String id = (String) attributes.get("id");
			String name = (String) attributes.get("name");

			if(formTag.attr("id") == id && formTag.attr("name") == name)
			{
				return i;
			}
		}

		return -1;
	}
	private static int get_formTag_index(JSONArray formTags, String formTag_id, String formTag_name) {
		for(int i=0; i<formTags.size(); i++)
		{
			JSONObject attributes = (JSONObject) ((JSONObject) formTags.get(i)).get("attributes");
			String id = (String) attributes.get("id");
			String name = (String) attributes.get("name");

			if(formTag_id.equals(id) && formTag_name.equals(name))
			{
				return i;
			}
		}

		return -1;
	}
}
