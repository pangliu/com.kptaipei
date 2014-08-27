package com.kptaipei.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectivityMonitor extends BroadcastReceiver{
	
	private static final IntentFilter CONNECTIVITY_INTENT_FILTER = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
    public static interface Delegate {
        public void onNetworkAvailable(NetworkInfo networkInfo);
        public void onNetworkUnavailable();
    }
    private final Context context;
    private boolean isConnected;
    private Delegate delegate;
	
    public ConnectivityMonitor(Context context) {
        this(context, null);
    }
    
    public ConnectivityMonitor(Context context, Delegate delegate) {
        this.context = context;
        setDelegate(delegate);
        isConnected = false;
    }
    
    public void start() {
        context.registerReceiver(this, CONNECTIVITY_INTENT_FILTER);
    }

    public void stop() {
        context.unregisterReceiver(this);
    }

    public void setDelegate(Delegate delegate) {
        this.delegate = delegate;
    }

    public boolean isNetworkAvailable() {
        return isConnected;
    }
    
	@Override
	public void onReceive(Context context, Intent intent) {
		final String action = intent.getAction();
        if (!action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
            return;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (activeNetworkInfo != null) && activeNetworkInfo.isConnected();
        if (delegate != null) {
            if (isConnected) {
                delegate.onNetworkAvailable(activeNetworkInfo);
            } else {
                delegate.onNetworkUnavailable();
            }
        }
	}
}
