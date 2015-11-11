package com.ghw.sdk.extend;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.ghw.sdk.extend.adapter.AppsAdapter;
import com.ghw.sdk.extend.adapter.BannerAdapter;
import com.ghw.sdk.extend.utils.ViewUtil;
import com.ghw.sdk.extend.widget.pullview.OnLoadMoreListener;
import com.ghw.sdk.extend.widget.pullview.OnRefreshListener;
import com.ghw.sdk.extend.widget.pullview.PullListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 应用墙界面
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class AppsFragment extends BaseFragment {

    private PullListView mLvApps;
    private AppsAdapter mAppsAdapter;

    private ViewPager mVpBanner;
    private BannerAdapter mBannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int layoutId = getIdentifier("ghw_sdk_fragment_apps", ViewUtil.DEF_RES_LAYOUT);
        View contentView = inflater.inflate(layoutId, container, false);
        initView(contentView);
        return contentView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        updateViewByConfiguration(newConfig);
    }

    private void initView(View contentView) {
        int lvAppsId = getIdentifier("lv_ghw_sdk_apps", ViewUtil.DEF_RES_ID);
        mLvApps = (PullListView) contentView.findViewById(lvAppsId);

        initHeaderView();


        mAppsAdapter = new AppsAdapter(getActivity());
        mLvApps.setAdapter(mAppsAdapter);

        mAppsAdapter.setOnViewClickListener(new AppsAdapter.OnViewClickListener() {
            @Override
            public void onInstallClicked(View view, int position) {
                Toast.makeText(getActivity(), "Install clicked--->>>" + position, Toast.LENGTH_SHORT).show();
            }
        });

        List<String> data = new ArrayList<>();
        for(int i = 0; i < 20; i++) {
            data.add("Item--> " + i);
        }
        mAppsAdapter.addAll(data, true);

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
        mLvApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "CLick--------->>>" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateViewByConfiguration(Configuration newConfig) {
        int dmWidth = getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams lp = mVpBanner.getLayoutParams();
        int height = dmWidth * 3 / 5;
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                height = dmWidth / 6;
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                height = dmWidth * 3 / 5;
                break;
            default:
                break;
        }
        if(null == lp) {
            lp = new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, height);
        } else {
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = height;
        }
        mVpBanner.setLayoutParams(lp);
    }

    /**
     * 头部视图
     */
    private void initHeaderView() {
        int headerLayoutId = getIdentifier("ghw_sdk_layout_apps_header", ViewUtil.DEF_RES_LAYOUT);
        View headerView = View.inflate(getActivity(), headerLayoutId, null);

        int bannerId = getIdentifier("vp_ghw_sdk_apps_header_banner", ViewUtil.DEF_RES_ID);
        mVpBanner = (ViewPager) headerView.findViewById(bannerId);

        updateViewByConfiguration(getResources().getConfiguration());
//        int dmWidth = getResources().getDisplayMetrics().widthPixels;
//        ViewGroup.LayoutParams lp = mVpBanner.getLayoutParams();
//        int height = dmWidth * 3 / 5;
//        if(null == lp) {
//            lp = new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, height);
//        } else {
//            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            lp.height = height;
//        }
//        mVpBanner.setLayoutParams(lp);
        mBannerAdapter = new BannerAdapter(getChildFragmentManager());
        mVpBanner.setAdapter(mBannerAdapter);

        mLvApps.addHeaderView(headerView, null, false);
//        mLvApps.addHeaderView(headerView);
    }

    private android.os.Handler mHandler = new android.os.Handler();
}
