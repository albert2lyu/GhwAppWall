package com.ghw.sdk.extend.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ghw.sdk.extend.BannerFragment;

/**
 * Banner page adapter
 * Created by yinglovezhuzhu@gmail.com on 2015/11/9.
 */
public class BannerAdapter extends FragmentStatePagerAdapter {

    public BannerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return BannerFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
