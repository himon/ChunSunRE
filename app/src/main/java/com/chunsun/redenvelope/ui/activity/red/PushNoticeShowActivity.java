package com.chunsun.redenvelope.ui.activity.red;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PushNoticeShowActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.tv_content)
    TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notice_show);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        mNavLeft.setOnClickListener(this);
        mNavIcon.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            String title = intent
                    .getStringExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE);
            String content = intent
                    .getStringExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_CONTENT);
            mContent.setText(content);
            initTitleBar(title, "", "", Constants.TITLE_TYPE_SAMPLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nav_icon:
            case R.id.tv_nav_left:
                back();
                break;
        }
    }
}
