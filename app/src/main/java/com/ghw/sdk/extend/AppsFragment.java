package com.ghw.sdk.extend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.ghw.sdk.extend.widget.pullview.OnLoadMoreListener;
import com.ghw.sdk.extend.widget.pullview.OnRefreshListener;
import com.ghw.sdk.extend.widget.pullview.PullListView;

import java.util.logging.Handler;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class AppsFragment extends BaseFragment {

    private PullListView mLvApps;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ghw_sdk_fragment_apps, container, false);
        initView(contentView);
        return contentView;
    }

    private void initView(View contentView) {
        mLvApps = (PullListView) contentView.findViewById(R.id.lv_ghw_sdk_apps);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.item_list_test, R.id.tv_item_test);
        mLvApps.setAdapter(adapter);

        for(int i = 0; i < 20; i++) {
            adapter.add("Item--> " + i);
        }
        adapter.notifyDataSetChanged();

        mLvApps.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLvApps.refreshCompleted();
                    }
                }, 5000);
            }
        });

        mLvApps.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mLvApps.loadMoreCompleted(true);
                    }
                }, 5000);
            }
        });
    }

    private android.os.Handler mHandler = new android.os.Handler();
}
