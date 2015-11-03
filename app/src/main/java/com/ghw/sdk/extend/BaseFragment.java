package com.ghw.sdk.extend;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class BaseFragment extends Fragment {

    protected final String TAG = this.getClass().getName();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(TAG, "onCreate--------->>>>>>>>>>>>>");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView--------->>>>>>>>>>>>>");
        Log.e(TAG, "onCreateView--------->>>>>>>>>>>>>");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.e(TAG, "onViewCreated--------->>>>>>>>>>>>>");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        Log.e(TAG, "onDestroyView--------->>>>>>>>>>>>>");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy--------->>>>>>>>>>>>>");
        super.onDestroy();
    }

    /**
     * 获取资源id，如果没有找到，返回0
     * @param name
     * @param defType
     * @return
     */
    public int getIdentifier(String name, String defType) {
        return getResources().getIdentifier(name, defType, getActivity().getPackageName());
    }
}
