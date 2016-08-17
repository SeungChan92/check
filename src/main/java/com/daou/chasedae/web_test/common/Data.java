package com.daou.chasedae.web_test.common;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Data {

	private static JSONObject JSONobject = new JSONObject();
	private static JSONArray pageArray = null; 

	public Data() { }

	public static void readFile()
	{

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
			JSONArray pages = new JSONArray();
			Data.JSONobject.put("pages", pages);
			pageArray = (JSONArray) Data.JSONobject.get("pages");
		}

		JSONObject newPage = new JSONObject();
		newPage.put("path", path);
		pageArray.add(newPage);
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

	
	private static JSONObject get_page(String path) {
		int page_index = -1;
		JSONObject page = null;

		// if there is no page array
		if(pageArray == null)
		{
			System.out.println("add_formTag - no page array");
			return null;
		}

		page_index = get_page_index(path);

		// if there is no such page in page array
		if(page_index == -1)
		{
			// log
			System.out.println("add_formTag - no such page");

			return null;
		}

		page = (JSONObject) pageArray.get(page_index);

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
	
	private static int get_page_index(String path) {
		for(int i=0; i<pageArray.size(); i++)
		{
			if(((JSONObject)pageArray.get(i)).get("path") == path)
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
}
