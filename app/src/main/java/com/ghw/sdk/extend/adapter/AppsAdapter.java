package com.ghw.sdk.extend.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.ghw.sdk.extend.R;
import com.ghw.sdk.extend.utils.ViewUtil;
import com.ghw.sdk.extend.widget.round.RoundImageView;

/**
 * 应用墙列表适配器
 * Created by yinglovezhuzhu@gmail.com on 2015/11/9.
 */
public class AppsAdapter extends BaseAdapter {

    private Context mContext;

    public AppsAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(null == convertView) {
            viewHolder = new ViewHolder();
            int itemLayoutId = getIdentifier("ghw_sdk_item_apps", ViewUtil.DEF_RES_LAYOUT);
            convertView = View.inflate(mContext, itemLayoutId, null);
            viewHolder.icon = (RoundImageView) convertView.findViewById(R.id.iv_ghw_sdk_item_apps_icon);
            viewHolder.title = (TextView) convertView.findViewById(R.id.tv_ghw_sdk_apps_item_title);
            viewHolder.memo = (TextView) convertView.findViewById(R.id.tv_ghw_sdk_apps_item_memo);
            viewHolder.install = (Button) convertView.findViewById(R.id.btn_ghw_sdk_apps_item_install);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

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

    private class ViewHolder {
        RoundImageView icon;
        TextView title;
        TextView memo;
        Button install;
    }
}
