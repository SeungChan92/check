package com.daou.verification.config;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config {
	
	public static JSONObject jsonObject = null;

	public static void load() {
		JSONParser parser = new JSONParser();
		
		try {
			Object obj;
			obj = parser.parse(new FileReader(
					"config/config.json"));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static String get(String key) {
		String value = null;
		
		value = (String) jsonObject.get(key);
		
		return value;
	}
}
