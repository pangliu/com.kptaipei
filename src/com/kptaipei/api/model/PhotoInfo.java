package com.kptaipei.api.model;

import java.util.Calendar;
import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.util.Log;

public class PhotoInfo {
	private static final String 
		KEY_ID = "id",
		KEY_IMAGES = "images",
		KEY_DATE_UPLOAD = "date_upload",
		KEY_DATE_TAKEN = "date_taken",
		KEY_LOCATION = "location",
		KEY_LATITUDE = "latitude",
		KEY_LONGITUDE = "longitude",
		KEY_TITLE = "title",
		KEY_LINK = "link",
		KEY_ISPRIMARY = "isprimary";
	
	protected String
		id,
		title,
		link,
		isprimary;
	protected Long
		dateUpload,
		dateTaken;
	protected Double
		latitude,
		longitude;
	protected ThumbnailInfo images;
	protected Location location;
	protected Calendar calendar = Calendar.getInstance() ;
	protected Date
		dateTimeUpload,
		dateTimeTaken;
	
	public PhotoInfo(JSONObject json) throws JSONException {
		this.id = json.optString(KEY_ID);
		this.title = json.optString(KEY_TITLE);
		this.link = json.optString(KEY_LINK);
		this.isprimary = json.optString(KEY_ISPRIMARY);
		this.dateUpload = json.getLong(KEY_DATE_UPLOAD);
		this.dateTaken = json.getLong(KEY_DATE_TAKEN);
		this.latitude = json.optDouble(KEY_LATITUDE);
		this.longitude = json.optDouble(KEY_LONGITUDE);
		this.images = new ThumbnailInfo(json.getJSONObject(KEY_IMAGES));
		
		this.location = new Location("kptaipei");
		location.setLatitude(json.getJSONObject(KEY_LOCATION).getDouble(KEY_LATITUDE));
		location.setLongitude(json.getJSONObject(KEY_LOCATION).getDouble(KEY_LONGITUDE));
		if(null != dateUpload) {
			this.calendar.setTimeInMillis(dateUpload);
			this.dateTimeUpload = calendar.getTime();
		}
		if(null != dateTaken) {
			this.calendar.setTimeInMillis(dateTaken);
			this.dateTimeTaken = calendar.getTime();
		}
	}
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public Date getDateTimeUpload() {
		return dateTimeUpload;
	}
	public Date getDateTimeTaken() {
		return dateTimeTaken;
	}
	public Location getLocation() {
		return location;
	}
	public String getLink() {
		return link;
	}
	public String getIsprimary() {
		return isprimary;
	}
	public ThumbnailInfo getImages() {
		return images;
	}
}
