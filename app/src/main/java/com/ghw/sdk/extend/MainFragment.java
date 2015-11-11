package com.ghw.sdk.extend;

import android.content.res.Configuration;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;

import com.ghw.sdk.extend.utils.ViewUtil;

/**
 * 主界面Fragment
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class MainFragment extends BaseFragment {

    private ImageButton mIBtnTitleLogo;
    private ImageView mIvTitleDivider;
    private ImageView mIvTitleLogoWords;
    private ImageButton mIBtnTitleClose;

    private FragmentTabHost mFtTabHost;

    private PopupWindow mPopMenu;
    private RadioGroup mRgMenu;

    private int mCurrentTab = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ghw_sdk_fragment_main, container, false);

        initView(contentView);

        return contentView;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        switch (newConfig.orientation) {
            case Configuration.ORIENTATION_LANDSCAPE:
                break;
            case Configuration.ORIENTATION_PORTRAIT:
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ghw_sdk_ibtn_title_logo:
                showMenu();
                break;
            case R.id.ghw_sdk_ibtn_title_close:
                exit();
                break;
            default:
                break;
        }
    }

    private void initView(View contentView) {
        mIBtnTitleLogo = (ImageButton) contentView.findViewById(R.id.ghw_sdk_ibtn_title_logo);
        mIvTitleDivider = (ImageView) contentView.findViewById(R.id.ghw_sdk_iv_title_divider);
        mIvTitleLogoWords = (ImageView) contentView.findViewById(R.id.ghw_sdk_iv_title_logo_word);
        mIBtnTitleClose = (ImageButton) contentView.findViewById(R.id.ghw_sdk_ibtn_title_close);
        mFtTabHost = (FragmentTabHost) contentView.findViewById(R.id.ghw_sdk_fth_main_tabs);
        mFtTabHost.setup(getActivity(), getChildFragmentManager(), R.id.ghw_sdk_fl_main_container);

        mIBtnTitleLogo.setOnClickListener(this);
        mIBtnTitleClose.setOnClickListener(this);

        addTabs(mFtTabHost);

        initPopMenu();

        mFtTabHost.setCurrentTab(mCurrentTab);
        switch (mCurrentTab) {
            case 0:
                mRgMenu.check(getIdentifier("ghw_sdk_rbtn_main_menu_apps", ViewUtil.DEF_RES_ID));
                break;
            case 1:
                mRgMenu.check(getIdentifier("ghw_sdk_rbtn_main_menu_info", ViewUtil.DEF_RES_ID));
                break;
            case 2:
                mRgMenu.check(getIdentifier("ghw_sdk_rbtn_main_menu_more", ViewUtil.DEF_RES_ID));
                break;
            default:
                break;
        }
    }

    /**
     * 初始化弹出菜单
     */
    private void initPopMenu() {
        mPopMenu = new PopupWindow(getActivity());
        mPopMenu.setBackgroundDrawable(new ColorDrawable());
        mPopMenu.setFocusable(true);
        mRgMenu = (RadioGroup) View.inflate(getActivity(), getIdentifier("ghw_sdk_layout_extend_menu", "layout"), null);
        mRgMenu.setOnCheckedChangeListener(mMenuCheckChangedListener);
        mPopMenu.setContentView(mRgMenu);
        mPopMenu.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopMenu.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    /**
     * Menu选择监听
     */
    private RadioGroup.OnCheckedChangeListener mMenuCheckChangedListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == getIdentifier("ghw_sdk_rbtn_main_menu_apps", ViewUtil.DEF_RES_ID)) {
                mCurrentTab = 0;
            } else if(checkedId == getIdentifier("ghw_sdk_rbtn_main_menu_info", ViewUtil.DEF_RES_ID)) {
                mCurrentTab = 1;
            } else if(checkedId == getIdentifier("ghw_sdk_rbtn_main_menu_more", ViewUtil.DEF_RES_ID)) {
                mCurrentTab = 2;
            }
            mFtTabHost.setCurrentTab(mCurrentTab);
            hideMenu();
        }
    };

    private void showMenu() {
        mPopMenu.showAsDropDown(mIBtnTitleLogo);
    }

    private void hideMenu() {
        mPopMenu.dismiss();
    }

    private void addTabs(FragmentTabHost tabHost) {

        tabHost.addTab(tabHost.newTabSpec("apps").setIndicator("apps"), AppsFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("information").setIndicator("information"), InformationFragment.class, null);

        tabHost.addTab(tabHost.newTabSpec("more").setIndicator("more"), MoreFragment.class, null);
    }
}
