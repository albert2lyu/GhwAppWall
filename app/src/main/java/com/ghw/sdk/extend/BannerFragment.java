package com.ghw.sdk.extend;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ghw.sdk.extend.utils.ViewUtil;
import com.ghw.sdk.extend.widget.round.RoundImageView;

/**
 * Banner fragment
 * Created by yinglovezhuzhu@gmail.com on 2015/11/9.
 */
public class BannerFragment extends BaseFragment{

    private RoundImageView mIvBanner;

    public static BannerFragment newInstance() {
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mIvBanner = new RoundImageView(getActivity());
        mIvBanner.setScaleType(ImageView.ScaleType.CENTER_CROP);
        mIvBanner.setCornerRadius(25);
        int fillColorId = getIdentifier("ghw_sdk_bg_color_light_white_theme", ViewUtil.DEF_RES_COLOR);
        mIvBanner.setFillColor(getResourceColor(fillColorId));

        mIvBanner.setImageResource(R.drawable.test_image1);
        return mIvBanner;
    }
}
