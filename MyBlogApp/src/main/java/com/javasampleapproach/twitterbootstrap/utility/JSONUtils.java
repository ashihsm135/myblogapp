package com.javasampleapproach.twitterbootstrap.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.javasampleapproach.twitterbootstrap.utility.pdf.Employee;

public class JSONUtils {
	
	/**
	 * @name Method - 1
	 * @param json
	 * @return
	 * @throws JSONException
	 */
	public static Map<String, Object> jsonToMap(JSONObject json)
			throws JSONException {

		Map<String, Object> retMap = new LinkedHashMap<String, Object>();

		if (json != JSONObject.NULL) {
			retMap = toMap(json);
		}
		return retMap;
	}


    /**
     * 
     * @param object
     * @return
     * @throws JSONException
     */
	public static Map<String, Object> toMap(JSONObject object)
			throws JSONException {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		Iterator<String> keysItr = object.keys();
		while (keysItr.hasNext()) {
			String key = keysItr.next();
			Object value = object.get(key);

			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			map.put(key, value);
		}
		return map;
	}


    /**
     * Method - 2
     * @param array
     * @return
     * @throws JSONException
     */
	public static List<Object> toList(JSONArray array) throws JSONException {

		List<Object> list = new ArrayList<Object>();
		for (int i = 0; i < array.length(); i++) {
			Object value = array.get(i);
			if (value instanceof JSONArray) {
				value = toList((JSONArray) value);
			}

			else if (value instanceof JSONObject) {
				value = toMap((JSONObject) value);
			}
			list.add(value);
		}
		return list;
	}


   /**
    * Method - 3
    * @param jsonObject
    * @param beanClass
    * @return
    */
	public static Object getBeanByJsonString(String jsonObject, Class beanClass) {

		Gson gson = new Gson();
		@SuppressWarnings("unchecked")
		Object obj = gson.fromJson(jsonObject, beanClass);
		return obj;
	}


    /**
     * 
     * @param beanClass
     * @return
     */
	public static String getJsonStringByBean(Object beanClass) {

		Gson gson = new Gson();
		String jsonString = gson.toJson(beanClass);
		return jsonString;
	}
	
	public static void main(String [] args){
		
		String message;
		JSONObject json = new JSONObject();
		try{
			json.put("name", "student");
			JSONArray array = new JSONArray();
			JSONObject item = new JSONObject();
			item.put("information", "test");
			item.put("id", 3);
			item.put("name", "course1");
			array.put(item);
			json.put("course", array);
			message = json.toString();
			System.out.println(message);
			
			//Method - 1 From JSONObject To Map
			Map<String, Object> map = new HashMap<String,Object>();
			map = toMap(item);
			System.out.println(map);
			
			//Method - 2 From JSONArray to List
			List<Object> list = new ArrayList<Object>();
			list = toList(array);
			System.out.println(list);
			
			//Method - 3 From JSONString to Bean Object
			Employee emp = new Employee();
			emp.setId(100);
			emp.setName("ASHISH");
			emp.setSalary(1000.0);
			Gson gson = new Gson();
			String josnstring = gson.toJson(emp); 
            
			Employee obj = (Employee)getBeanByJsonString(josnstring,Employee.class);
			System.out.println(gson.toJson(obj));
			
		}catch(Exception e){
			
		}
	}

}
