package com.kptaipei.fragment;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.kptaipei.R;
import com.kptaipei.api.APIHelper;
import com.kptaipei.api.model.Category;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BaseFragment extends Fragment{
	protected static final int DIALOG_ID_PROGRESS = 0;
    //private Handler handler;
    //private AlertDialog dialog;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //handler = new Handler();
    }
    
    public static ProgressDialog createLoadingDialog(Context context) {
    	ProgressDialog dialog = new ProgressDialog(context);
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.setMessage(context.getResources().getString(R.string.updating));
        return dialog;
    }
    
    class GetCategoryTask extends AsyncTask<String, Integer, List<Category>> {
		private ListView listView;
		private ArrayAdapter<String> adapter;
		private List<Category> categories;
		private APIHelper apiHelper;
		private ProgressDialog loadingDialog;
		
		public GetCategoryTask(Context context, ListView listView, ArrayAdapter<String> adapter) {
			this.listView = listView;
			this.adapter = adapter;
			apiHelper = new APIHelper(context);
		}
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			if(null == loadingDialog) {
				loadingDialog = createLoadingDialog(getActivity());
			}
			loadingDialog.show();
		}
		
		@Override
		protected List<Category> doInBackground(String... params) {
			String categoryId = params[0];
			try {
				categories = apiHelper.getCategory(categoryId);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return categories;
		}
		
		@Override
		protected void onPostExecute(List<Category> categories) {
			loadingDialog.dismiss();
			if(null != categories) {
				adapter.clear();
				for(Category category : categories) {
					adapter.add(category.getTitle());
				}
				listView.setAdapter(adapter);
				adapter.notifyDataSetChanged();
			}
		}
	}
}
