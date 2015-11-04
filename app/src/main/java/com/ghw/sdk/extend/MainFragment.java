package com.ghw.sdk.extend;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.ghw.sdk.extend.widget.TabItemView;

/**
 * 主界面Fragment
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class MainFragment extends BaseFragment {

    private FragmentTabHost mFtTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ghw_sdk_fragment_main, container, false);

        initView(contentView);

        return contentView;
    }

    private void initView(View contentView) {
        mFtTabHost = (FragmentTabHost) contentView.findViewById(R.id.fth_main_tabs);
        mFtTabHost.setup(getActivity(), getChildFragmentManager(), R.id.fl_main_container);

        addTabs(mFtTabHost);

        contentView.findViewById(R.id.btn_main_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (View.VISIBLE == mFtTabHost.getVisibility()) {
                    hidTabs();
                } else {
                    showTabs();
                }
            }
        });

    }

    private void addTabs(FragmentTabHost tabHost) {

        TabItemView appTabItem = new TabItemView(getActivity());
        appTabItem.setBackgroundColorByResource(getIdentifier("bg_color_dark_yellow_theme", "color"));
        appTabItem.setToggleIconResouce(getIdentifier("selector_app_wall_yellow_theme", "drawable"));
        appTabItem.setText(getIdentifier("app_wall", "string"));

        TabItemView InfoTabItem = new TabItemView(getActivity());
        InfoTabItem.setBackgroundColorByResource(getIdentifier("bg_color_dark_yellow_theme", "color"));
        InfoTabItem.setToggleIconResouce(getIdentifier("selector_information_yellow_theme", "drawable"));
        InfoTabItem.setText(getIdentifier("information", "string"));

        TabItemView moreTabItem = new TabItemView(getActivity());
        moreTabItem.setBackgroundColorByResource(getIdentifier("bg_color_dark_yellow_theme", "color"));
        moreTabItem.setToggleIconResouce(getIdentifier("selector_more_yellow_theme", "drawable"));
        moreTabItem.setText(getIdentifier("more", "string"));


        tabHost.addTab(tabHost.newTabSpec("apps").setIndicator(appTabItem), AppsFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("information").setIndicator(InfoTabItem), InformationFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("more").setIndicator(moreTabItem), MoreFragment.class, null);
//        TabItemView appTabItem = new TabItemView(getActivity());
//        appTabItem.setBackgroundColorByResource(getIdentifier("bg_color_dark_yellow_theme", "color"));
//        appTabItem.setToggleIconResouce(getIdentifier("selector_app_wall_yellow_theme", "drawable"));
//        appTabItem.setText(getIdentifier("app_wall", "string"));
//
//        TabItemView InfoTabItem = new TabItemView(getActivity());
//        InfoTabItem.setBackgroundColorByResource(getIdentifier("bg_color_dark_yellow_theme", "color"));
//        InfoTabItem.setToggleIconResouce(getIdentifier("selector_information_yellow_theme", "drawable"));
//        InfoTabItem.setText(getIdentifier("information", "string"));
//
//        TabItemView moreTabItem = new TabItemView(getActivity());
//        moreTabItem.setBackgroundColorByResource(getIdentifier("bg_color_dark_yellow_theme", "color"));
//        moreTabItem.setToggleIconResouce(getIdentifier("selector_more_yellow_theme", "drawable"));
//        moreTabItem.setText(getIdentifier("more", "string"));
//
//
//        tabHost.addTab(tabHost.newTabSpec("apps").setIndicator("Apps"), AppsFragment.class, null);
//
//        tabHost.addTab(tabHost.newTabSpec("information").setIndicator("Info"), InformationFragment.class, null);
//
//        tabHost.addTab(tabHost.newTabSpec("more").setIndicator("More"), MoreFragment.class, null);
    }

    private void showTabs() {
        mFtTabHost.setVisibility(View.VISIBLE);
        mFtTabHost.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.anim_in_from_left));
    }

    private void hidTabs() {
        Animation anim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_out_to_left);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mFtTabHost.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mFtTabHost.startAnimation(anim);
    }
}
