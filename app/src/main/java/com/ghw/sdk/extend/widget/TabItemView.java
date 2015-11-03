package com.ghw.sdk.extend.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.ghw.sdk.extend.utils.ViewUtil;


/**
 * Tab Item 视图自定义组合控件
 * Created by yinglovezhuzhu@gmail.com on 2015/11/2.
 */
public class TabItemView extends ALinearLayout {

    private TextView mTvToggle;
    private ImageView mIvDivider;

    public TabItemView(Context context) {
        super(context);

        initView(context);
    }

    public TabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    public TabItemView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView(context);
    }

    /**
     * 设置图标资源，支持selector，类型类似于复选框
     * @param resId 图标资源id
     */
    public void setToggleIconResouce(int resId) {
        mTvToggle.setCompoundDrawablesWithIntrinsicBounds(0, resId, 0, 0);
    }

    /**
     * 设置图标，支持selector，类型类似于复选框
     * @param drawable 图标Drawable
     */
    public void setToggleIconResouce(Drawable drawable) {
        mTvToggle.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
    }

    /**
     * 设置文字
     * @param resId 文字字符资源id
     */
    public void setText(int resId) {
        mTvToggle.setText(resId);
    }

    /**
     * 设置文字
     * @param text 文字
     */
    public void setText(CharSequence text) {
        mTvToggle.setText(text);
    }

    /**
     * 设置分隔线显示/隐藏
     * @param visibility 显示、隐藏
     */
    public void setDividerVisibility(int visibility) {
        mIvDivider.setVisibility(visibility);
    }

    public void setBackgroundColorByResource(int resId) {
        setBackgroundColor(getResourceColor(resId));
    }

    private void initView(Context context) {
        int layoutId = getIdentifier("ghw_sdk_layout_tab_item", ViewUtil.DEF_RES_LAYOUT);
        inflate(context, layoutId, this);

        int toggleId = getIdentifier("tv_tab_item_toggle", ViewUtil.DEF_RES_ID);
        mTvToggle = (TextView) findViewById(toggleId);

        int dividerId = getIdentifier("iv_tab_item_divider", ViewUtil.DEF_RES_ID);
        mIvDivider = (ImageView) findViewById(dividerId);
    }
}
