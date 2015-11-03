/*
 * Copyright (C) 2014. The Android Open Source Project.
 *
 *         yinglovezhuzhu@gmail.com
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.ghw.sdk.extend.widget.pullview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghw.sdk.extend.utils.ViewUtil;
import com.ghw.sdk.extend.widget.ALinearLayout;

/**
 * Usage The header view.
 *
 * @author yinglovezhuzhu@gmail.com
 */
public class PullFooterView extends ALinearLayout {

    private RotateAnimation mRotateAnimation;
    private int mCurrentDegree = 0;
    /**
     * The arrow image view.
     */
    private ImageView mIvProgress;
    /**
     * The tips textview.
     */
    private TextView mTvTitle;

    /**
     * The head content height.
     */
    int mViewHeight;

    /**
     * Instantiates a new ab list view header.
     *
     * @param context the context
     */
    public PullFooterView(Context context) {
        super(context);
        initView(context);
    }

    /**
     * Instantiates a new ab list view header.
     *
     * @param context the context
     * @param attrs   the attrs
     */
    public PullFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    /**
     * Set arrow image visibility
     *
     * @param visibility
     */
    public void setArrowVisibility(int visibility) {
        mIvProgress.setVisibility(visibility);
    }

    /**
     * Set title text visibility.
     *
     * @param visibility
     */
    public void setTitleVisibility(int visibility) {
        mTvTitle.setVisibility(visibility);
    }

    /**
     * Set title text
     *
     * @param text
     */
    public void setTitleText(CharSequence text) {
        mTvTitle.setText(text);
    }

    /**
     * Set title text
     *
     * @param resId
     */
    public void setTitleText(int resId) {
        mTvTitle.setText(resId);
    }

    /**
     * 旋转图标
     * @param paddingBottom
     */
    public void rotate(int paddingBottom) {
        int fromDegree = mCurrentDegree;
        mCurrentDegree = mViewHeight + paddingBottom * IPullView.OFFSET_RATIO;
        mRotateAnimation = new RotateAnimation(fromDegree, mCurrentDegree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setFillAfter(true);
        mIvProgress.startAnimation(mRotateAnimation);
        // 透明度
        int alpha = 255 * (mViewHeight + paddingBottom) / mViewHeight;
        alpha = alpha > 255 ? 255 : alpha;
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            mIvProgress.setAlpha(alpha);
        } else {
            try {
                mIvProgress.setImageAlpha(alpha);
            } catch (NoSuchMethodError e) {
                mIvProgress.setAlpha(alpha);
            }
        }
    }

    /**
     * 进度条模式
     */
    public void progress() {
        mRotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        mRotateAnimation.setInterpolator(new LinearInterpolator());
        mRotateAnimation.setFillAfter(true);
        mRotateAnimation.setRepeatMode(Animation.RESTART);
        mRotateAnimation.setRepeatCount(-1);
        mRotateAnimation.setDuration(1000);
        mIvProgress.startAnimation(mRotateAnimation);
    }

    /**
     * Gets the header height.
     *
     * @return the header height
     */
    public int getViewHeight() {
        return mViewHeight;
    }

    /**
     * Set title text color
     *
     * @param color
     * @throws
     */
    public void setTitleTextColor(int color) {
        mTvTitle.setTextColor(color);
    }

    /**
     * Set Background color
     *
     * @param color
     * @throws
     */
    public void setBackgroundColor(int color) {
        setBackgroundColor(color);
    }

    /**
     * Inits the view.
     *
     * @param context the context
     */
    private void initView(Context context) {

        int layoutId = getIdentifier("ghw_sdk_layout_pullview_footer", ViewUtil.DEF_RES_LAYOUT);
        inflate(context, layoutId, this);

        int progressId = getIdentifier("iv_ghw_sdk_pullview_footer_progress", ViewUtil.DEF_RES_ID);
        mIvProgress = (ImageView) findViewById(progressId);
        int titleId = getIdentifier("tv_ghw_sdk_pullview_footer_title", ViewUtil.DEF_RES_ID);
        mTvTitle = (TextView) findViewById(titleId);

        //Get height of this header view.
        ViewUtil.measureView(this);
        mViewHeight = this.getMeasuredHeight();
    }

}
