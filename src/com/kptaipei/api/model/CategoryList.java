package com.kptaipei.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryList {
	
	public final static String
		KEY_ID = "id",
		KEY_NAME = "name";
	protected String
		name,
		id;
	
	public CategoryList(JSONObject json) throws JSONException {
		this.id = json.getString(KEY_ID);
		this.name = json.getString(KEY_NAME);
	}
	
	public String getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
}
