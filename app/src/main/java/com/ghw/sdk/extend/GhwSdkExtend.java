package com.ghw.sdk.extend;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.ghw.sdk.extend.utils.GhwUtil;
import com.ghw.sdk.extend.utils.ViewUtil;

import java.util.Arrays;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/11/11.
 */
public class GhwSdkExtend {

    private static GhwSdkExtend mInstance = null;

    private WindowManager mWindowManager;

    private WindowManager.LayoutParams mLpIBtnFlow;
    private ImageButton mIBtnFlow;

    private boolean mInitialized = false;

    private int mDisplayWidth = 0;

    private GhwSdkExtend() {

    }

    static GhwSdkExtend getInstance() {
        synchronized (GhwSdkExtend.class) {
            if(null == mInstance) {
                mInstance = new GhwSdkExtend();
            }
            return mInstance;
        }
    }

    public static void showEntryFlowIcon(Activity activity) {
        getInstance().showEntryFlow(activity);
    }

    public static void hideEntryFlowIcon() {
        getInstance().hideEntryFlow();
    }

    void initialize(Context context) {
        if(!GhwUtil.checkPermissions(context, Arrays.asList(Manifest.permission.SYSTEM_ALERT_WINDOW))) {
            throw new IllegalStateException("Permission denied, need permission: " + Manifest.permission.SYSTEM_ALERT_WINDOW);
        }
        if(mInitialized) {
            return;
        }
        Context appContext = context.getApplicationContext();
        mWindowManager = (WindowManager) appContext.getSystemService(Context.WINDOW_SERVICE);
        mDisplayWidth = appContext.getResources().getDisplayMetrics().widthPixels;
        initLayoutParams(appContext);

        initLogButton(appContext);

        mInitialized = true;
    }

    void showEntryFlow(Activity activity) {

        initialize(activity);

        try {
            mWindowManager.addView(mIBtnFlow, mLpIBtnFlow);
        } catch (Exception e) {
            // do nothing
        }
    }

    void hideEntryFlow() {
        if(null != mWindowManager) {
            if(null != mIBtnFlow) {
                try {
                    mWindowManager.removeView(mIBtnFlow);
                } catch (Exception e) {
                    // do nothing
                }
            }
        }
    }

    void setFlowVisible(boolean visible) {
        if(null == mIBtnFlow) {
            return;
        }
        mIBtnFlow.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    private void initLayoutParams(Context context) {
        mLpIBtnFlow = new WindowManager.LayoutParams();
        mLpIBtnFlow.type = WindowManager.LayoutParams.TYPE_PHONE;
        mLpIBtnFlow.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLpIBtnFlow.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLpIBtnFlow.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLpIBtnFlow.format = PixelFormat.RGBA_8888;
        mLpIBtnFlow.gravity = Gravity.LEFT | Gravity.TOP;
    }

    /**
     * 初始化入口悬浮按钮
     * @param context
     */
    private void initLogButton(final Context context) {
        mIBtnFlow = new ImageButton(context);
        mIBtnFlow.setBackgroundColor(Color.TRANSPARENT);
        int iconResId = context.getResources().getIdentifier("ghw_sdk_ic_logo",
                ViewUtil.DEF_RES_DRAWABLE, context.getPackageName());
        mIBtnFlow.setImageResource(iconResId);
        mIBtnFlow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GhwSdkExtendActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        mIBtnFlow.setOnTouchListener(new View.OnTouchListener() {

            float startX = 0f;
            float startY = 0f;

            float downX = 0f;
            float downY = 0f;
            int statusBarHeight = 0;
            boolean start = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                try {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            startX = event.getRawX();
                            startY = event.getRawY();
                            downX = event.getX();
                            downY = event.getY();
                            Rect rect = new Rect();
                            mIBtnFlow.getWindowVisibleDisplayFrame(rect);
                            statusBarHeight = rect.top;

                            start = true;
                            break;
                        case MotionEvent.ACTION_MOVE:
                            if (start && Math.abs(event.getRawX() - startX) < 5 && Math.abs(event.getRawY() - startY) < 5) {
                                start = false;
                                return true;
                            }
                            mLpIBtnFlow.x = (int) (event.getRawX() - downX);
                            mLpIBtnFlow.y = (int) (event.getRawY() - downY - statusBarHeight);
                            mWindowManager.updateViewLayout(mIBtnFlow, mLpIBtnFlow);
//                            mSharePrefHelper.saveInt(SP_KEY_FLOW_BUTTON_X, mLpIBtnFlow.x);
//                            mSharePrefHelper.saveInt(SP_KEY_FLOW_BUTTON_Y, mLpIBtnFlow.y);
                            return true;
                        case MotionEvent.ACTION_UP:
                            if(mLpIBtnFlow.x < mDisplayWidth / 2) {
                                mLpIBtnFlow.x = 0;
                            } else {
                                mLpIBtnFlow.x = mDisplayWidth;
                            }
                            mWindowManager.updateViewLayout(mIBtnFlow, mLpIBtnFlow);
                            start = false;
                            return Math.abs(event.getRawX() - startX) > 5 || Math.abs(event.getRawY() - startY) > 5;
                        default:
                            break;

                    }
                } catch (Exception e) {
                    // do nothing
                }
                return false;
            }
        });

    }
}
