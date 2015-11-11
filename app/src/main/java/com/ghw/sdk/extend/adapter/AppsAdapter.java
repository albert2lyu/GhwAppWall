package com.ghw.sdk.extend.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ghw.sdk.extend.utils.ViewUtil;
import com.ghw.sdk.extend.widget.round.RoundImageView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 应用墙列表适配器
 * Created by yinglovezhuzhu@gmail.com on 2015/11/9.
 */
public class AppsAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mData = new ArrayList<>();

    private OnViewClickListener mViewClickListener;

    public AppsAdapter(Context context) {
        this.mContext = context;
    }

    public void addAll(Collection<String> data, boolean notifyDataSetChanged) {
        if(null == data || data.isEmpty()) {
            return;
        }
        mData.addAll(data);
        if(notifyDataSetChanged) {
            notifyDataSetChanged();;
        }
    }

    public void clear(boolean notifyDataSetChanged) {
        mData.clear();
        if(notifyDataSetChanged) {
            notifyDataSetChanged();;
        }
    }

    /**
     * 设置item内控件点击事件
     * @param listener
     */
    public void setOnViewClickListener(OnViewClickListener listener) {
        this.mViewClickListener = listener;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public String getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(null == convertView) {
            viewHolder = new ViewHolder();
            int itemLayoutId = getIdentifier("ghw_sdk_item_apps", ViewUtil.DEF_RES_LAYOUT);
            convertView = View.inflate(mContext, itemLayoutId, null);
            int iconId = getIdentifier("iv_ghw_sdk_item_apps_icon", ViewUtil.DEF_RES_ID);
            viewHolder.icon = (RoundImageView) convertView.findViewById(iconId);
            int titleId = getIdentifier("tv_ghw_sdk_apps_item_title", ViewUtil.DEF_RES_ID);
            viewHolder.title = (TextView) convertView.findViewById(titleId);
            int memoId = getIdentifier("tv_ghw_sdk_apps_item_memo", ViewUtil.DEF_RES_ID);
            viewHolder.memo = (TextView) convertView.findViewById(memoId);
            int installId = getIdentifier("btn_ghw_sdk_apps_item_install", ViewUtil.DEF_RES_ID);
            viewHolder.install = (Button) convertView.findViewById(installId);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.title.setText(getItem(position));
        viewHolder.install.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mViewClickListener) {
                    mViewClickListener.onInstallClicked(v, position);
                }
            }
        });
        return convertView;
    }

    /**
     * 获取资源id，如果没有找到，返回0
     * @param name
     * @param defType
     * @return
     */
    protected int getIdentifier(String name, String defType) {
        return mContext.getResources().getIdentifier(name, defType, mContext.getPackageName());
    }

    public interface OnViewClickListener {
        /**
         * Install button clicked.
         * @param view button
         * @param position item position
         */
        public void onInstallClicked(View view, int position);
    }

    private class ViewHolder {
        RoundImageView icon;
        TextView title;
        TextView memo;
        Button install;
    }

}
