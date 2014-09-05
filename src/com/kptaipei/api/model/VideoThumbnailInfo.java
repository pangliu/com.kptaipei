package com.kptaipei.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class VideoThumbnailInfo {
	private final static String 
		KEY_DEFAULT = "default",
		KEY_MEDIUM = "medium",
		KEY_HIGH = "high",
		KEY_STANDARD = "standard",
		KEY_MAXRES = "maxres",
		KEY_URL = "url",
		KEY_WIDTH = "width",
		KEY_HEIGHT = "height";
	
	public class ThumbnailKind {
		protected String url;
		protected int 
			width,
			height;
		public ThumbnailKind(JSONObject kindJson) throws JSONException {
			this.url = kindJson.getString(KEY_URL);
			this.width = kindJson.getInt(KEY_WIDTH);
			this.height = kindJson.getInt(KEY_HEIGHT);
		}
		
		public String getUrl() {
			return url;
		}
		
		public int getWidth() {
			return width;
		}
		
		public int getHeight() {
			return height;
		}
	}
	
	protected ThumbnailKind
		defaultInfo,
		mediumInfo,
		highInfo,
		standarInfo,
		maxresInfo;
		
	public VideoThumbnailInfo(JSONObject json) throws JSONException {
		this.defaultInfo = new ThumbnailKind(json.optJSONObject(KEY_DEFAULT));
		this.mediumInfo = new ThumbnailKind(json.optJSONObject(KEY_MEDIUM));
		this.highInfo = new ThumbnailKind(json.optJSONObject(KEY_HIGH));
		this.standarInfo = new ThumbnailKind(json.optJSONObject(KEY_STANDARD));
		this.maxresInfo = new ThumbnailKind(json.optJSONObject(KEY_MAXRES));
	}
	
	public ThumbnailKind getDefaultInfo() {
		return defaultInfo;
	}
	
	public ThumbnailKind getMediumInfo() {
		return mediumInfo;
	}
	
	public ThumbnailKind getHighInfo() {
		return highInfo;
	}
	
	public ThumbnailKind getStandarInfo() {
		return standarInfo;
	}
	
	public ThumbnailKind getMaxresInfo() {
		return maxresInfo;
	}
}
