package com.kptaipei;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.kptaipei.api.APIHelper;
import com.kptaipei.api.model.CategoryList;
import com.kptaipei.network.ConnectivityMonitor;
import com.kptaipei.fragment.NewsFragment;
import com.kptaipei.fragment.PoliciesFragment;
import com.kptaipei.fragment.RealKpFragment;

import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.Menu;

public class HomeActivity extends BaseActivity implements ConnectivityMonitor.Delegate {
	private final static String TAG = HomeActivity.class.getSimpleName();
	private ConnectivityMonitor connectivityMonitor;
	private APIHelper api;
	private GetCategoryListTask categoryListTask;
	private ActionBar actionBar;
	private AlertDialog networkUnavailableDialog;
	private ProgressDialog loadingDialog;
	private List<CategoryList> categoryList = new ArrayList<CategoryList>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		connectivityMonitor = new ConnectivityMonitor(this, this);
		networkUnavailableDialog = networkUnavailableDialog(this);
		api = new APIHelper(this);
//		categoryListTask = new GetCategoryListTask();
//		categoryListTask.execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		connectivityMonitor.start();
		if(0 == categoryList.size()) {
			Log.d(TAG, "category = 0");
			categoryListTask = new GetCategoryListTask();
			categoryListTask.execute();
		}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		connectivityMonitor.stop();
	}
	@Override
	public void onNetworkAvailable(NetworkInfo networkInfo) {
		networkUnavailableDialog.dismiss();
//		Log.d(TAG, "networkavailable");
//		if(0 == categoryList.size()) {
//			Log.d(TAG, "in network category = 0 ");
//			switch(categoryListTask.getStatus()) {
//			case FINISHED:
//				Log.d(TAG, "finished");
//				categoryListTask.cancel(true);
//				Log.d(TAG, "task status: " + categoryListTask.getStatus());
//				//categoryListTask.execute();
//				break;
//			case PENDING:
//				Log.d(TAG, "pending");
//				break;
//			case RUNNING:
//				Log.d(TAG, "running");
//				break;
//			}
//		}
	}

	@Override
	public void onNetworkUnavailable() {
		networkUnavailableDialog.show();
	}
	
	private void initActionBar() {
		actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        actionBar.show();
        final PoliciesFragment f1 = new PoliciesFragment();
        final RealKpFragment f2 = new RealKpFragment();
        final NewsFragment f3 = new NewsFragment();
        for(CategoryList category : categoryList) {
        	final Fragment fragment;
        	switch(Integer.valueOf(category.getId())) {
        	default:
        	case 40:
        		fragment = f1;
        		break;
        	case 41:
        		fragment = f2;
        		break;
        	case 42:
        		fragment = f3;
        		break;
        	}
        	ActionBar.TabListener tabListener = new ActionBar.TabListener() {
				@Override
				public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
					// TODO Auto-generated method stub
					ft.hide(fragment);
				}
				@Override
				public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
					// TODO Auto-generated method stub
					if(fragment.isAdded()) {
						ft.show(fragment);
					} else {
						ft.add(R.id.home_fragment_container, fragment);
					}
				}
				@Override
				public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
					// TODO Auto-generated method stub
				}
			};
        	ActionBar.Tab tab = actionBar.newTab()
            		.setTag(category)
            		.setText(category.getName())
            		.setTabListener(tabListener);
        	actionBar.addTab(tab);
        }
	}
	
	class GetCategoryListTask extends AsyncTask<String, Integer, List<CategoryList>> {
		private Exception error = null;
		
		@Override
        protected void onPreExecute() {
            super.onPreExecute();
            if(null == loadingDialog) {
                loadingDialog = createLoadingDialog(HomeActivity.this);
            }
            loadingDialog.show();
		}
		
		@Override
		protected List<CategoryList> doInBackground(String... params) {
			try {
				categoryList = api.getCategoryList();
			} catch (Exception e) {
				error = e;
			}
			return categoryList;
		}
		
		@Override
		protected void onPostExecute(List<CategoryList> list) {
			loadingDialog.dismiss();
			if(null == error) {
				initActionBar();
			} else {
				handleError(error);
			}
		}
		
		private void handleError(Exception error) {
			if(error instanceof ClientProtocolException) {
				getClientProtocolErrorAlertDialog();
			} else if(error instanceof JSONException){
				getClientProtocolErrorAlertDialog();
			}  else if(error instanceof Exception) {
				getClientProtocolErrorAlertDialog();
			}
		}
	}

}
