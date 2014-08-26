package com.kptaipei.fragment;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;

import com.kptaipei.R;
import com.kptaipei.api.APIHelper;
import com.kptaipei.api.model.Category;

import android.app.Activity;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class NewPoliciesFragment extends Fragment {
	
	private APIHelper api;
	private GetCategoryTask getCategoryTask;
	private List<Category> categories = new ArrayList<Category>();
	private ListView newPolicyList;
	private ArrayAdapter<String> newPolicyAdapter;
	
	@Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_new_policies , container, false);
        api = new APIHelper(getActivity());
        newPolicyAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        findView(view);
        return view;
	}
	
	@Override
    public void onResume() {
        super.onResume();
        if(!isHidden()) {
        	getCategoryTask = new GetCategoryTask();
        	getCategoryTask.execute();
        }
	}
	
	private void findView(View view) {
		newPolicyList = (ListView) view.findViewById(R.id.new_policies_list);
	}
	
	class GetCategoryTask extends AsyncTask<String, Integer, List<Category>> {

		@Override
		protected List<Category> doInBackground(String... params) {
			try {
				categories = api.getCategory("40");
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
			for(Category category : categories) {
				newPolicyAdapter.add(category.getTitle());
			}
			newPolicyList.setAdapter(newPolicyAdapter);
		}
	}
}
