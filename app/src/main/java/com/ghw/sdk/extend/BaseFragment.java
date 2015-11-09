package com.ghw.sdk.extend;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.res.Resources;
import android.os.Build;
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
public class BaseFragment extends Fragment implements View.OnClickListener {

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
    protected int getIdentifier(String name, String defType) {
        return getResources().getIdentifier(name, defType, getActivity().getPackageName());
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected int getResourceColor(int resId) {
        int color = 0;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP_MR1) {
            color = getResources().getColor(resId);
        } else {
            try {
                color = getResources().getColor(resId, null);
            } catch (NoSuchMethodError e) {
                color = getResources().getColor(resId);
            }
        }
        return color;
    }


    /**
     * 带动画的入栈，动画的模式是，Fragment从右侧推入，从左侧退出，出栈的动画是左侧推入，右侧退出。<br/><br/>
     * <font color="red">注意：仅当关联Activity是{@linkplain GhwSdkExtendActivity} 才有效</font>
     * @param fragment 入栈的Fragment
     */
    protected void addFragmentToStackWithAnimation(Fragment fragment) {
        Activity activity = getActivity();
        if (activity instanceof GhwSdkExtendActivity) {
            ((GhwSdkExtendActivity) activity).addFragmentToStackWithAnimation(fragment);
        }
    }

    /**
     * 入栈<br/>
     * <font color="red">注意：仅当关联Activity是{@linkplain GhwSdkExtendActivity} 才有效</font><br/>
     * @param fragment 入栈的Fragment
     */
    protected void addFragmentToStack(Fragment fragment) {
        Activity activity = getActivity();
        if (activity instanceof GhwSdkExtendActivity) {
            ((GhwSdkExtendActivity) activity).addFragmentToStack(fragment);
        }
    }

    /**
     * 出栈，返回上一个Fragment<br/>
     * <font color="red">注意：仅当关联Activity是{@linkplain GhwSdkExtendActivity} 才有效</font><br/>
     */
    protected void popBack() {
        Activity activity = getActivity();
        if (activity instanceof GhwSdkExtendActivity) {
            ((GhwSdkExtendActivity) activity).popBack();
        }
    }

    /**
     * 退出<br/><br/>
     * <font color="red">注意：仅当关联Activity是{@linkplain GhwSdkExtendActivity} 才有效</font><br/>
     */
    protected void exit() {
        Activity activity = getActivity();
        if (activity instanceof GhwSdkExtendActivity) {
            ((GhwSdkExtendActivity) activity).exit();
        }
    }
    @Override
    public void onClick(View v) {

    }
}
