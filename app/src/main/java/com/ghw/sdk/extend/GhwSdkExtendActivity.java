package com.ghw.sdk.extend;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.WindowManager;

import com.ghw.sdk.extend.utils.ViewUtil;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class GhwSdkExtendActivity extends FragmentActivity {

    private FragmentManager mFragmentManager;

    private int mContainerId = 0;

    private int mInFromLeftAnim = 0;
    private int mInFromRightAnim = 0;
    private int mOutToLeftAnim = 0;
    private int mOutToRightAnim = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

        getWindow().setGravity(Gravity.LEFT);

        int layoutId = getIdentifier("ghw_sdk_activity_extend", ViewUtil.DEF_RES_LAYOUT);
        setContentView(layoutId);

        mContainerId = getIdentifier("fl_main_container", ViewUtil.DEF_RES_ID);

        mInFromLeftAnim = getIdentifier("ghw_sdk_anim_in_from_left", ViewUtil.DEF_RES_ANIM);
        mInFromRightAnim = getIdentifier("ghw_sdk_anim_in_from_right", ViewUtil.DEF_RES_ANIM);
        mOutToLeftAnim = getIdentifier("ghw_sdk_anim_out_to_left", ViewUtil.DEF_RES_ANIM);
        mOutToRightAnim = getIdentifier("ghw_sdk_anim_out_to_right", ViewUtil.DEF_RES_ANIM);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = getResources().getDisplayMetrics().widthPixels - 200;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
//        lp.width = getResources().getDisplayMetrics().widthPixels - 200;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        getWindow().setAttributes(lp);

        // 添加默认的Fragment
        addFragmentToStack(new MainFragment());
    }

    @Override
    protected void onResume() {
        super.onResume();
        GhwSdkExtend.getInstance().setFlowVisible(false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        GhwSdkExtend.getInstance().setFlowVisible(true);
    }

    @Override
    public void onBackPressed() {
        popBack();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    /**
     * 入栈
     * @param fragment 入栈的Fragment
     */
    void addFragmentToStack(Fragment fragment) {
        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.replace(mContainerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * 带动画的入栈，动画的模式是，Fragment从右侧推入，从左侧退出，出栈的动画是左侧推入，右侧退出。
     * @param fragment 入栈的Fragment
     */
    void addFragmentToStackWithAnimation(Fragment fragment) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(mInFromRightAnim, mOutToLeftAnim, mInFromLeftAnim, mOutToRightAnim);
        ft.replace(mContainerId, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * 回退(如果入栈使用了动画，那么出栈自动回有动画)<br/>
     * 当栈内只有一个Fragment的时候，退出Activity
     */
    void popBack() {
        if(mFragmentManager.getBackStackEntryCount() > 1) {
            // 栈内的Fragment大于1，退到上一个
            mFragmentManager.popBackStack(mFragmentManager.getBackStackEntryAt(mFragmentManager.getBackStackEntryCount() - 1).getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } else {
            // 栈内的Fragment小于于1，退出Activity
            exit();
        }
    }

    void exit() {
        finish();
        int outToTopAnimId = getIdentifier("ghw_sdk_anim_out_to_top", ViewUtil.DEF_RES_ANIM);
        overridePendingTransition(outToTopAnimId, outToTopAnimId);
    }

    /**
     * 获取资源id，如果没有找到，返回0
     * @param name
     * @param defType
     * @return
     */
    protected int getIdentifier(String name, String defType) {
        return getResources().getIdentifier(name, defType, getPackageName());
    }
}
