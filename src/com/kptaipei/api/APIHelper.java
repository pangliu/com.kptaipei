package com.kptaipei.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.kptaipei.R;
import com.kptaipei.api.model.AlbumsList;
import com.kptaipei.api.model.Category;
import com.kptaipei.api.model.CategoryList;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

public class APIHelper {
	private static final String TAG = APIHelper.class.getSimpleName();
	private final HttpClient client;
    private Context context;
    private String apiKey;
    
	public APIHelper(Context context) {
		this.context = context;
		HttpParams httpParameters = new BasicHttpParams();
        HttpProtocolParams.setContentCharset(httpParameters, HTTP.UTF_8);
        HttpProtocolParams.setHttpElementCharset(httpParameters, HTTP.UTF_8);
        this.client = new DefaultHttpClient();
        client.getParams().setParameter("http.protocol.version", HttpVersion.HTTP_1_1);
        client.getParams().setParameter("http.protocol.content-charset", HTTP.UTF_8);
        this.apiKey = context.getString(R.string.api_key);
	}
	
	/**
	 * @author pang
	 * @return List<CategoryList>
	 */
	public List<CategoryList> getCategoryList() throws ClientProtocolException, IOException, JSONException {
		String url = context.getString(R.string.category_url) + "?" + apiKey;
		String msg = null;
		boolean isSuccess = false;
		List<CategoryList> categoryLists = new ArrayList<CategoryList>();
		JSONObject response = new JSONObject(doGet(url));
		Log.d(TAG, "getCategoryList json: " + response.toString());
		if(response.has("errorMessage")) {
			msg = response.getString("errorMessage");
		}
		if(response.has("isSuccess")) {
			isSuccess = response.getBoolean("isSuccess");
		}
		if(response.has("data")) {
			JSONArray dataJosn = response.getJSONArray("data");
			for(int i=0 ; i<dataJosn.length() ; i++) {
				try {
					JSONObject json = dataJosn.getJSONObject(i);
					CategoryList category = new CategoryList(json);
					categoryLists.add(category);
				} catch (JSONException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		if(isSuccess) {
			return categoryLists;
		} else {
			String errMsg = (null == msg) ? context.getResources().getString(R.string.api_error_message) : msg;
			AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			dialog.setMessage(errMsg)
            	.create()
            	.show();
			return null;
		}
	}
	
	/**
	 * @author pang
	 * @param categoryId
	 * @return List<Category>
	 */
	public List<Category> getCategory(String categoryId) throws ClientProtocolException, IOException, JSONException {
		String url = context.getString(R.string.category_url) + categoryId + "?" + apiKey;
		String msg = null;
		boolean isSuccess = false;
		List<Category> categories = new ArrayList<Category>();
		JSONObject response = new JSONObject(doGet(url));
		Log.d(TAG, "getCategory json: " + response.toString());
		if(response.has("errorMessage")) {
			msg = response.getString("errorMessage");
		}
		if(response.has("isSuccess")) {
			isSuccess = response.getBoolean("isSuccess");
		}
		if(response.has("data")) {
			JSONArray dataJosn = response.getJSONArray("data");
			for(int i=0 ; i<dataJosn.length() ; i++) {
				try {
					JSONObject json = dataJosn.getJSONObject(i);
					Category category = new Category(json);
					categories.add(category);
				} catch (JSONException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		if(isSuccess) {
			return categories;
		} else {
			String errMsg = (null == msg) ? context.getResources().getString(R.string.api_error_message) : msg;
			AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			dialog.setMessage(errMsg)
            	.create()
            	.show();
			return null;
		}
	}
	
	/**
	 * @author pang
	 * @return List<AlbumsList>
	 */
	public List<AlbumsList> getAlbumsList() throws ClientProtocolException, JSONException, IOException {
		String url = context.getString(R.string.albums_url) + "?" + apiKey;
		String msg = null;
		boolean isSuccess = false;
		List<AlbumsList> albumsList = new ArrayList<AlbumsList>();
		JSONObject response = new JSONObject(doGet(url));
		Log.d(TAG, "getAlbumsList json: " + response.toString());
		if(response.has("errorMessage")) {
			msg = response.getString("errorMessage");
		}
		if(response.has("isSuccess")) {
			isSuccess = response.getBoolean("isSuccess");
		}
		if(response.has("data")) {
			JSONArray dataJosn = response.getJSONArray("data");
			for(int i=0 ; i<dataJosn.length() ; i++) {
				try {
					JSONObject json = dataJosn.getJSONObject(i);
					AlbumsList album = new AlbumsList(json);
					albumsList.add(album);
				} catch (JSONException e) {
					e.printStackTrace();
					continue;
				}
			}
		}
		if(isSuccess) {
			return albumsList;
		} else {
			String errMsg = (null == msg) ? context.getResources().getString(R.string.api_error_message) : msg;
			AlertDialog.Builder dialog = new AlertDialog.Builder(context);
			dialog.setMessage(errMsg)
            	.create()
            	.show();
			return null;
		}
	}
	
	protected String doGet(String url) throws ClientProtocolException, IOException {
		Log.d(TAG, "url: " + url);
		HttpGet get = new HttpGet(url);
		HttpResponse response = client.execute(get);
		HttpEntity resEntity = response.getEntity();
		String result = EntityUtils.toString(resEntity);
		return result;
	}

}
