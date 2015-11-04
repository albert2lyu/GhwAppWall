package com.ghw.sdk.extend;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/11/2.
 */
public class MoreFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setTextSize(40);
        tv.setTextColor(Color.RED);
        tv.setText("This is more pge");
        tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragmentToStackWithAnimation(new DetailFragment());
            }
        });
        return tv;
    }
}
