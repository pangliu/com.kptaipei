package com.kptaipei.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class ThumbnailInfo {
	private final static String
		KEY_SMALL = "small",
		KEY_SMALL_SQUARE = "small_square",
		KEY_LARGE_SQUARE = "large_square",
		KEY_THUMBNAIL = "thumbnail",
		KEY_MEDIUM = "medium",
		KEY_LARGE = "large",
		KEY_ORIGINAL = "original";
	
	private String
		small,
		smallSquare,
		largeSquare,
		thumbnail,
		medium,
		large,
		original;
	
	public ThumbnailInfo(JSONObject json) throws JSONException {
		this.small = json.getString(KEY_SMALL);
		this.smallSquare = json.getString(KEY_SMALL_SQUARE);
		this.largeSquare = json.getString(KEY_LARGE_SQUARE);
		this.thumbnail = json.getString(KEY_THUMBNAIL);
		this.medium = json.getString(KEY_MEDIUM);
		this.large = json.getString(KEY_LARGE);
		this.original = json.getString(KEY_ORIGINAL);
	}
	
	public String getSmall() {
		return small;
	}
	
	public String getSmallSquare() {
		return smallSquare;
	}
	
	public String getLarge() {
		return large;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
	
	public String getMedium() {
		return medium;
	}
	
	public String getLargeSquare() {
		return largeSquare;
	}
	
	public String getOriginal() {
		return original;
	}
}
