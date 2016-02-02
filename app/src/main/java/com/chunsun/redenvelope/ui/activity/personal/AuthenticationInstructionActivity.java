package com.chunsun.redenvelope.ui.activity.personal;

import android.os.Bundle;
import android.view.View;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.base.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * 认证说明Activity
 */
public class AuthenticationInstructionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication_instruction);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("认证说明", "", "", Constants.TITLE_TYPE_SAMPLE);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void click(View v) {

    }
}
