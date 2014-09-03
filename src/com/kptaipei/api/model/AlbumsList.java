package com.kptaipei.api.model;

import java.util.Calendar;
import java.util.Date;

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
		title,
		description,
		link;
	protected int videos;
	protected Long
		dateCreate,
		dateUpdate;
	protected Date
		dateTimeCreat,
		dateTimeUpdate;
	protected Calendar calender = Calendar.getInstance() ;
	protected Thumbnails thumbnails;
	
	public AlbumsList(JSONObject json) throws JSONException {
		this.id = json.getString(KEY_ID);
		this.photos = json.getString(KEY_PHOTOS);
		this.videos = json.getInt(KEY_VIDEOS);
		this.title = json.getString(KEY_TITLE);
		this.description = json.getString(KEY_DESCRIPTION);
		this.dateCreate = json.getLong(KEY_DATE_CREATE);
		this.dateUpdate = json.getLong(KEY_DATE_UPDATE);
		this.link = json.getString(KEY_LINK);
		this.thumbnails = new Thumbnails(json.getJSONObject(KEY_THUMBNAILS));
		if(null != dateCreate) {
			this.calender.setTimeInMillis(dateCreate);
			this.dateTimeCreat = calender.getTime();
		}
		if(null != dateUpdate) {
			this.calender.setTimeInMillis(dateUpdate);
			this.dateTimeUpdate = calender.getTime();
		}
	}
	
	public String getId() {
		return id;
	}
	
	public String getPhotos() {
		return photos;
	}
	
	public int getVideos() {
		return videos;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getDateTimeCreat() {
		return dateTimeCreat;
	}
	
	public Date getDateTimeUpdate() {
		return dateTimeUpdate;
	}
	
	public String getLink() {
		return link;
	}
	
	public Thumbnails getThumbnails() {
		return thumbnails;
	}
}
