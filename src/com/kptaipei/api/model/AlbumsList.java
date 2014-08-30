package com.kptaipei.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class AlbumsList {
	private static final String 
		KEY_ID = "id",
		KEY_PHOTOS = "photos",
		KEY_VIDEOS = "videos",
		KEY_TITLE = "title",
		KEY_DESCRIPTION = "description",
		KEY_DATE_CREATE = "date_create",
		KEY_DATE_UPDATE = "date_update",
		KEY_LINK = "link",
		KEY_THUMBNAILS = "thumbnails";
	
	protected String
		id,
		photos,
		videos,
		title,
		description,
		dateCreate,
		dateUpdate,
		link;
	
	protected Thumbnails thumbnails;
	
	public AlbumsList(JSONObject json) throws JSONException {
		this.id = json.getString(KEY_ID);
		this.photos = json.getString(KEY_PHOTOS);
		this.videos = json.getString(KEY_VIDEOS);
		this.title = json.getString(KEY_TITLE);
		this.description = json.getString(KEY_DESCRIPTION);
		this.dateCreate = json.getString(KEY_DATE_CREATE);
		this.dateUpdate = json.getString(KEY_DATE_UPDATE);
		this.link = json.getString(KEY_LINK);
		this.thumbnails = new Thumbnails(json.getJSONObject(KEY_THUMBNAILS));
	}
	
	public String getId() {
		return id;
	}
	
	public String getPhotos() {
		return photos;
	}
	
	public String getVideos() {
		return videos;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getDateCreate() {
		return dateCreate;
	}
	
	public String getDateUpdate() {
		return dateUpdate;
	}
	
	public String getLink() {
		return link;
	}
	
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
}
