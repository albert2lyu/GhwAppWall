package com.ghw.sdk.extend;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.ghw.sdk.extend.adapter.BannerAdapter;
import com.ghw.sdk.extend.utils.ViewUtil;
import com.ghw.sdk.extend.widget.pullview.OnLoadMoreListener;
import com.ghw.sdk.extend.widget.pullview.OnRefreshListener;
import com.ghw.sdk.extend.widget.pullview.PullListView;
import com.ghw.sdk.extend.widget.round.RoundImageView;

import java.util.logging.Handler;

/**
 * 应用墙界面
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class AppsFragment extends BaseFragment {

    private PullListView mLvApps;

    private ViewPager mVpBanner;
    private BannerAdapter mBannerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ghw_sdk_fragment_apps, container, false);
        initView(contentView);
        return contentView;
    }

    private void initView(View contentView) {
        mLvApps = (PullListView) contentView.findViewById(R.id.lv_ghw_sdk_apps);

        initHeaderView();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.ghw_sdk_item_apps, R.id.tv_ghw_sdk_apps_item_title);
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
        mLvApps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "CLick--------->>>" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 头部视图
     */
    private void initHeaderView() {
        int headerLayoutId = getIdentifier("ghw_sdk_layout_apps_header", ViewUtil.DEF_RES_LAYOUT);
        View headerView = View.inflate(getActivity(), headerLayoutId, null);

        int bannerId = getIdentifier("vp_ghw_sdk_apps_header_banner", ViewUtil.DEF_RES_ID);
        mVpBanner = (ViewPager) headerView.findViewById(bannerId);

        int dmWidth = getResources().getDisplayMetrics().widthPixels;
        ViewGroup.LayoutParams lp = mVpBanner.getLayoutParams();
        int height = dmWidth * 3 / 5;
        if(null == lp) {
            lp = new ViewGroup.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT, height);
        } else {
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = height;
        }
        mVpBanner.setLayoutParams(lp);
        mBannerAdapter = new BannerAdapter(getChildFragmentManager());
        mVpBanner.setAdapter(mBannerAdapter);

        mLvApps.addHeaderView(headerView, null, false);
//        mLvApps.addHeaderView(headerView);
    }

    private android.os.Handler mHandler = new android.os.Handler();
}
