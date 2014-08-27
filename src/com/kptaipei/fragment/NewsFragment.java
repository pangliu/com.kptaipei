package com.kptaipei.fragment;

import com.kptaipei.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class NewsFragment extends BaseFragment{
	
	private GetCategoryTask getCategoryTask;
	private ListView newsList;
	private ArrayAdapter<String> newsAdapter;
	
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
        newsAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        findView(view);
        return view;
	}
	
    public void onResume() {
        super.onResume();
        if(!isHidden()) {
        	getCategoryTask = new GetCategoryTask(getActivity(), newsList, newsAdapter);
        	getCategoryTask.execute("42");
        }
	}
	
	private void findView(View view) {
		newsList = (ListView) view.findViewById(R.id.new_policies_list);
	}
}
