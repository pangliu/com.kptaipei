package com.kptaipei;

import java.util.ArrayList;
import java.util.List;
import com.kptaipei.api.APIHelper;
import com.kptaipei.api.model.CategoryList;
import com.kptaipei.network.ConnectivityMonitor;
import com.kptaipei.fragment.NewsFragment;
import com.kptaipei.fragment.PoliciesFragment;
import com.kptaipei.fragment.RealKpFragment;
import android.net.NetworkInfo;
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
	private ActionBar actionBar;
	private AlertDialog networkUnavailableDialog;
	
	public enum TabInfo {
		POLICY(R.string.tab_policy),
		REAL(R.string.tab_real),
		NEWS(R.string.tab_news);
		
		public final int nameResId;
        private TabInfo(int nameResId) {
            this.nameResId = nameResId;
        }
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		connectivityMonitor = new ConnectivityMonitor(this, this);
		networkUnavailableDialog = networkUnavailableDialog(this);
		api = new APIHelper(this);
		initActionBar();
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
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		connectivityMonitor.stop();
	}
	@Override
	public void onNetworkAvailable(NetworkInfo networkInfo) {
		networkUnavailableDialog.dismiss();
		Log.d(TAG, "networkavailable");
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
        
        for(TabInfo tabInfo : TabInfo.values()) {
        	final Fragment fragment;
        	switch(tabInfo) {
        	default:
        	case POLICY:
        		fragment = f1;
        		break;
        	case REAL:
        		fragment = f2;
        		break;
        	case NEWS:
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
				}
			};
        	ActionBar.Tab tab = actionBar.newTab()
            		.setTag(tabInfo)
            		.setText(tabInfo.nameResId)
            		.setTabListener(tabListener);
        	actionBar.addTab(tab);
        }
	}
	
}
