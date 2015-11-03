package com.ghw.sdk.extend;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by yinglovezhuzhu@gmail.com on 2015/10/30.
 */
public class DetailFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        TextView tv = new TextView(getActivity());
        tv.setTextSize(40);
        tv.setTextColor(Color.RED);
        tv.setText("This is a detail page");tv.setClickable(true);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Activity activity = getActivity();
                if (activity instanceof GhwSdkExtendActivity) {
                    ((GhwSdkExtendActivity) activity).popBack();
                }
            }
        });
        return tv;
    }
}
