package com.kptaipei;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;

public class BaseActivity extends Activity {
	protected static final int DIALOG_ID_PROGRESS = 0;
    private Handler handler;
    private AlertDialog dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handler = new Handler();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    
    public void getClientProtocolErrorAlertDialog() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                alterClientProtocolError();
            }
        });
    }
    
    protected void alterClientProtocolError() {
        String errMsg = getResources().getString(R.string.api_error_message);
        dialog = new AlertDialog.Builder(this)
            .setMessage(errMsg)
            .setNeutralButton(getResources().getString(R.string.ok), null)
            .create();
        dialog.show();
    }
    
    public static ProgressDialog createLoadingDialog(Context context) {
    	ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(true);
        dialog.setIndeterminate(true);
        dialog.setMessage(context.getResources().getString(R.string.updating));
		return dialog;
    }
    
    // 當沒有開啟網路功能的提示 dialog
    public static AlertDialog networkUnavailableDialog(final Context context) {
        AlertDialog.Builder networkBuilder = new AlertDialog.Builder(context);
        networkBuilder
            .setMessage(R.string.cannot_connect_please_check_your_connection)
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // 進入設定網路頁面
                    Intent intent = new Intent(Settings.ACTION_SETTINGS);
                    context.startActivity(intent);
                }
            })
            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        return networkBuilder.create();
    }
}
