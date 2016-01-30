package com.chunsun.redenvelope.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.utils.StringUtil;

/**
 * Created by Administrator on 2015/8/22.
 * 设置中的item
 */
public class SettingItem extends LinearLayout {

    private TextView mTvContent;
    private TextView mTvData;
    private ImageView mIvArrow;

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.layout_setting_item, this, true);
        mTvContent = (TextView) findViewById(R.id.tv_content);
        mTvData = (TextView) findViewById(R.id.tv_data);
        mIvArrow = (ImageView) findViewById(R.id.iv_arrow);
    }

    public void setContent(String content) {
        mTvContent.setText(content);
    }

    public void setContent(String content, String data) {
        mTvContent.setText(content);
        mTvData.setText(data);
        mTvData.setVisibility(View.VISIBLE);
    }

    public void setContentTextBlack(String content, String data) {
        mTvContent.setText(content);
        mTvContent.setTextColor(getResources().getColor(android.R.color.black));
        mTvData.setText(data);
        mTvData.setVisibility(View.VISIBLE);

    }

    public void setContentNoArrow(String content, String data) {
        setContentTextBlack(content, data);
        mIvArrow.setVisibility(View.INVISIBLE);
    }


    public String getData() {
        return StringUtil.textview2String(mTvData);
    }

    public void setData(String data) {
        mTvData.setText(data);
    }

}
