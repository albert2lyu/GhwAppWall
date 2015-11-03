package com.ghw.sdk.extend.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.ghw.sdk.extend.utils.ViewUtil;

/**
 * 标题栏
 * Created by yinglovezhuzhu@gmail.com on 2015/11/2.
 */
public class Titlebar extends ALinearLayout {

    private ImageView mIvLogo;
    private ImageButton mIBtnOpt;
    private View mViewDivider;

    public Titlebar(Context context) {
        super(context);
        initView(context);
    }

    public Titlebar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public Titlebar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public Titlebar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }



    private void initView(Context context) {
        int layoutId = getIdentifier("ghw_sdk_layout_titlebar", ViewUtil.DEF_RES_LAYOUT);
        inflate(context, layoutId, this);

        int logoId = getIdentifier("iv_ghw_sdk_titlebar_logo", ViewUtil.DEF_RES_ID);
        mIvLogo = (ImageView) findViewById(logoId);

        int optId = getIdentifier("ibtn_ghw_sdk_titlebar_opt", ViewUtil.DEF_RES_ID);
        mIBtnOpt = (ImageButton) findViewById(optId);

        int dividerId = getIdentifier("view_ghw_sdk_titlebar_divider", ViewUtil.DEF_RES_ID);
        mViewDivider = findViewById(dividerId);
    }
}
