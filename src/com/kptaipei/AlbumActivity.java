package com.kptaipei;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.kptaipei.api.APIHelper;
import com.kptaipei.api.model.AlbumsInfo;
import com.kptaipei.api.model.AlbumsList;
import com.kptaipei.api.model.PhotoInfo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class AlbumActivity extends BaseActivity{
	private final static String TAG = AlbumActivity.class.getSimpleName();
	private List<AlbumsList> albumsList = new ArrayList<AlbumsList>();
	private AlbumsInfo albumsInfo;
	private APIHelper apiHelper;
	private GetAlbumListTask albumTask;
	private GetAlbumInfoTask albumInfoTask;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_album);
		apiHelper = new APIHelper(this);
		albumTask = new GetAlbumListTask();
		albumTask.execute();
		albumInfoTask = new GetAlbumInfoTask();
		albumInfoTask.execute();
	}
	
	class GetAlbumListTask extends AsyncTask<String, Integer, List<AlbumsList>>{

		@Override
		protected List<AlbumsList> doInBackground(String... params) {
			// TODO Auto-generated method stub
			try {
				albumsList = apiHelper.getAlbumsList();
				for(AlbumsList info : albumsList) {
					Log.d(TAG, "title: " + info.getTitle() + " + " + "creatTime: " + sdf.format(info.getDateTimeCreat()));
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return albumsList;
		}
	}
	
	class GetAlbumInfoTask extends AsyncTask<String, Integer, AlbumsInfo> {

		@Override
		protected AlbumsInfo doInBackground(String... params) {
			try {
				albumsInfo = apiHelper.getAlbumsInfo("72157646938324252");
				Log.d(TAG, "title: " + albumsInfo.getTitle());
				for(PhotoInfo photo : albumsInfo.getPhotos()) {
					Log.d(TAG, "uploadtime: " + sdf.format(photo.getDateTimeUpload()) + 
							" + location:( " + photo.getLocation().getLatitude() + "," + photo.getLocation().getLongitude());
				}
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
	}
}
