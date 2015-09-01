package com.chunsun.redenvelope.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;

/**
 * Created by Administrator on 2015/8/22.
 * 设置中的item
 */
public class SettingItem extends LinearLayout {

    private TextView mTvContent;

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_setting_item, this, true);
        mTvContent = (TextView) findViewById(R.id.tv_content);
    }

    public void setContent(String content) {
        mTvContent.setText(content);
    }


}
