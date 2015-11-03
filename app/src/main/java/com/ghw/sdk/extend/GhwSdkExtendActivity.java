package com.ghw.sdk.extend;

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
        lp.windowAnimations = R.style.CustomActivityAnimation;
        getWindow().setAttributes(lp);

        // 添加默认的Fragment
        addFragmentToStack(new MainFragment());
    }

    @Override
    public void onBackPressed() {
        popBack();
        Log.e("BBBBB", "onBackPressed------------------");
    }

    /**
     * 入栈
     * @param fragment
     */
    void addFragmentToStack(Fragment fragment) {
        // Add the fragment to the activity, pushing this transaction
        // on to the back stack.
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.replace(R.id.fl_main_container, fragment);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.addToBackStack(null);
        ft.commit();
    }

    /**
     * 回退
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
        overridePendingTransition(R.anim.anim_out_to_left, R.anim.anim_out_to_left);
    }
}
