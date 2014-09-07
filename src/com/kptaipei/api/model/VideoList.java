package com.kptaipei.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class VideoList {
	private final static String
		KEY_ID = "id",
		KEY_TITLE = "title",
		KEY_DESCRIPTION = "description",
		KEY_PUBLISHED_AT = "publishedAt",
		KEY_THUMBNAILS = "thumbnails",
		KEY_LINK = "link",
		KEY_VIDEO_COUNT = "video_count";
	
	protected String
		id,
		title,
		description,
		publishedAt,
		link;
	protected int videoCount;
	protected VideoDetailInfo thumbnailInfo;
	
	
	public VideoList(JSONObject json) throws JSONException {
		this.id = json.getString(KEY_ID);
		this.title = json.getString(KEY_TITLE);
		this.description = json.getString(KEY_DESCRIPTION);
		this.publishedAt = json.getString(KEY_PUBLISHED_AT);
		this.link = json.getString(KEY_LINK);
		this.videoCount = json.getInt(KEY_VIDEO_COUNT);
		this.thumbnailInfo = new VideoDetailInfo(json.getJSONObject(KEY_THUMBNAILS));
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getPublishedAt() {
		return publishedAt;
	}
	
	public String getLink() {
		return link;
	}
	
	public int getVideoCount() {
		return videoCount;
	}
	
	public VideoDetailInfo getThumbnailInfo() {
		return thumbnailInfo;
	}
}
