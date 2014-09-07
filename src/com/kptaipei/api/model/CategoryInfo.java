package com.kptaipei.api.model;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryInfo {
	
	public final static String
		KEY_ID = "id",
		KEY_TITLE = "title",
		KEY_CONTENT = "content",
		KEY_POST_DATE = "post_date",
		KEY_AUTHOR = "author",
		KEY_LAST_MODIFY = "last_modify",
		KEY_URL = "url",
		KEY_CATEGORY_NAME = "category_name",
		KEY_CATEGORY_ID = "category_id",
		KEY_PLAIN_CONTENT = "plain_content",
		KEY_THUMBNAIL = "thumbnail";
		
	
	protected String
		id,
		title,
		content,
		postDate,
		author,
		lastModify,
		url,
		categoryName,
		categoryId,
		plainContent,
		thumbnail;
	
	public CategoryInfo(JSONObject json) throws JSONException {
		this.id = json.getString(KEY_ID);
		this.title = json.getString(KEY_TITLE);
		this.content = json.getString(KEY_CONTENT);
		this.postDate = json.getString(KEY_POST_DATE);
		this.author = json.getString(KEY_AUTHOR);
		this.lastModify = json.getString(KEY_LAST_MODIFY);
		this.url = json.getString(KEY_URL);
		this.categoryName = json.getString(KEY_CATEGORY_NAME);
		this.categoryId = json.getString(KEY_CATEGORY_ID);
		this.plainContent = json.getString(KEY_PLAIN_CONTENT);
		this.thumbnail = json.getString(KEY_THUMBNAIL);
	}
	
	public String getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getPostDate() {
		return postDate;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getLastModify() {
		return lastModify;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getCategoryName() {
		return categoryName;
	}
	
	public String getCategoryId() {
		return categoryId;
	}
	
	public String getPlainContent() {
		return plainContent;
	}
	
	public String getThumbnail() {
		return thumbnail;
	}
}
