package com.ghw.sdk.extend;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;

/**
 * 主界面Fragment
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class MainFragment extends BaseFragment {

    private ImageButton mIBtnTitleLogo;
    private ImageView mIvTitleDivider;
    private ImageView mIvTitleLogoWords;
    private ImageButton mIBtnTitleClose;

    private PopupWindow mPopMenu;

    private FragmentTabHost mFtTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.ghw_sdk_fragment_main, container, false);

        initView(contentView);

        return contentView;
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

        mFtTabHost.setCurrentTab(0);
    }

    /**
     * 初始化弹出菜单
     */
    private void initPopMenu() {
        mPopMenu = new PopupWindow(getActivity());
        mPopMenu.setBackgroundDrawable(new ColorDrawable());
        mPopMenu.setFocusable(true);
        View popMenuView = View.inflate(getActivity(), getIdentifier("ghw_sdk_layout_extend_menu", "layout"), null);
        mPopMenu.setContentView(popMenuView);
        mPopMenu.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopMenu.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

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
