package com.test;

import org.json.JSONArray;
import org.json.JSONObject;

public class testJSON {

	public static void main(String[] args) {
		JSONObject profileJSON = new JSONObject();
		profileJSON.put("name", "profileName");

		JSONObject key = new JSONObject();
		key.put("name", "enter");
		key.put("duration", "1");
		key.put("keyCode", "15");
		
		JSONObject key2 = new JSONObject();
		key2.put("name", "del");
		key2.put("duration", "2");
		key2.put("keyCode", "14");
		
		JSONArray profileKeys = new JSONArray();
		profileKeys.put(key);
		profileKeys.put(key2);

		profileJSON.put("keys", profileKeys);		
		System.out.println(profileJSON);
	}

}
