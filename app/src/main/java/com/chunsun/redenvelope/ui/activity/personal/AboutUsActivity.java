package com.chunsun.redenvelope.ui.activity.personal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 关于我们Activity
 */
public class AboutUsActivity extends BaseActivity {

    @Bind(R.id.tv_phonenum)
    TextView mTvPhoneNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("关于我们", "", "", Constants.TITLE_TYPE_SAMPLE);
        initEvent();
    }

    private void initEvent() {
        mNavIcon.setOnClickListener(this);
        mNavLeft.setOnClickListener(this);
        mTvPhoneNum.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_phonenum:
                calling();
                break;
        }
    }

    private void calling() {
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"
                + StringUtil.textview2String(mTvPhoneNum)));
        startActivity(intent);
    }
}
