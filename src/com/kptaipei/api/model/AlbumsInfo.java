package com.kptaipei.api.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class AlbumsInfo {
	private static final String 
		KEY_SET = "set",
		KEY_ID = "id",
		KEY_TITLE = "title",
		KEY_PHOTOS = "photos";
	
	protected String
		id,
		title;
	protected List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
	
	public AlbumsInfo(JSONObject json) throws JSONException {
		JSONObject setJson = json.getJSONObject(KEY_SET);
		this.id = setJson.optString(KEY_ID);
		this.title = setJson.optString(KEY_TITLE);
		JSONArray photoJsonArray = json.getJSONArray(KEY_PHOTOS);
		for(int i=0 ; i<photoJsonArray.length() ; i++){
			PhotoInfo photo;
			try {
				photo = new PhotoInfo(photoJsonArray.getJSONObject(i));
				this.photos.add(photo);
			} catch (JSONException e) {
				e.printStackTrace();
				continue;
			}
		}
	}
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public List<PhotoInfo> getPhotos() {
		return photos;
	}
}
