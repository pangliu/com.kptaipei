package com.kptaipei.fragment;

import com.kptaipei.R;
import com.kptaipei.fragment.BaseFragment.GetCategoryTask;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class RealKpFragment extends BaseFragment {
	
	private GetCategoryTask getCategoryTask;
	private ListView realKpList;
	private ArrayAdapter<String> realKpAdapter;
	
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
        realKpAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        findView(view);
        return view;
	}
	
	public void onResume() {
        super.onResume();
        if(!isHidden()) {
        	getCategoryTask = new GetCategoryTask(getActivity(), realKpList, realKpAdapter);
        	getCategoryTask.execute("41");
        }
	}
	
	private void findView(View view) {
		realKpList = (ListView) view.findViewById(R.id.new_policies_list);
	}
}
