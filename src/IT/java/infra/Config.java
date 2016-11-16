package infra;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Config implements Infra {

	private static JSONObject jsonObject_asp_root = null;
	private static JSONObject jsonObject_service_root = null;

	public static void init() {
		if (!(Config.is_loaded())) {
			Config.load();
		}
	}
	public static String get_fromAsp(String key) {
		return get(jsonObject_asp_root, key);
	}
	public static String get_fromService(String key) {
		return get(jsonObject_service_root, key);
	}
	public static String get_url(String pageClass_name) {

		String url = "";
		JSONObject jsonObject_urls = (JSONObject) Config.jsonObject_asp_root.get("urls");

		url = (String) jsonObject_urls.get(pageClass_name);

		return url;
	}

	// depth 1
	public static void load() {
		String targetService_name = null;
		
		load_asp_root();
		targetService_name = Config.get_fromAsp("targetService");
		load_service_root(targetService_name);
	}
	public static boolean is_loaded() {
		boolean loaded = false;

		if (Config.jsonObject_asp_root != null && Config.jsonObject_service_root != null)
		{
			loaded = true;
		}

		return loaded;
	}
	private static String get(JSONObject jsonObject, String key) {
		String value = null;

		value = (String) jsonObject.get(key);

		return value;
	}
	
	// depth 2
	private static void load_asp_root() {
		JSONParser parser = new JSONParser();
		String configFile_path = "src/IT/resources/config/asp.json";

		try {
			Object obj;
			obj = parser.parse(new FileReader(configFile_path));
			Config.jsonObject_asp_root = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private static void load_service_root(String service_name) {
		JSONParser parser = new JSONParser();
		String configFile_path = "src/IT/resources/config/" + service_name + ".json";

		try {
			Object obj;
			obj = parser.parse(new FileReader(configFile_path));
			Config.jsonObject_service_root = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}