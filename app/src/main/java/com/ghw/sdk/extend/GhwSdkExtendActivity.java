package com.ghw.sdk.extend;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class GhwSdkExtendActivity extends FragmentActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragmentManager = getSupportFragmentManager();

        getWindow().setGravity(Gravity.LEFT);

        setContentView(R.layout.ghw_sdk_activity_extend);

        WindowManager.LayoutParams lp = getWindow().getAttributes();
//        lp.width = getResources().getDisplayMetrics().widthPixels - 200;
//        lp.height = getResources().getDisplayMetrics().heightPixels;
//        lp.width = getResources().getDisplayMetrics().widthPixels - 200;
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.windowAnimations = R.style.ExtendActivityAnimation;
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
        ft.replace(R.id.fl_main_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * 带动画的入栈，动画的模式是，Fragment从右侧推入，从左侧退出，出栈的动画是左侧推入，右侧退出。
     * @param fragment 入栈的Fragment
     */
    void addFragmentToStackWithAnimation(Fragment fragment) {
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.anim_in_from_right, R.anim.anim_out_to_left, R.anim.anim_in_from_left, R.anim.anim_out_to_right);
        ft.replace(R.id.fl_main_container, fragment);
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
        overridePendingTransition(R.anim.anim_out_to_top, R.anim.anim_out_to_top);
    }
}
