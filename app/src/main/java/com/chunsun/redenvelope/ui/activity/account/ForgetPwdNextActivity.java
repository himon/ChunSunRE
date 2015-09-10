package com.chunsun.redenvelope.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.presenter.impl.ForgetPwdNextPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IForgetPwdNextView;
import com.chunsun.redenvelope.utils.manager.AppManager;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ForgetPwdNextActivity extends BaseActivity implements IForgetPwdNextView, View.OnClickListener {

    @Bind(R.id.et_input_password)
    EditText etPwd;
    @Bind(R.id.et_confirm_password)
    EditText etRepwd;
    @Bind(R.id.btn_complete)
    Button btnFinish;

    private ForgetPwdNextPresenter mPresenter;

    private String mMobile;
    private String mVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd_next);
        ButterKnife.bind(this);
        mPresenter = new ForgetPwdNextPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("忘记密码", "返回", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        btnFinish.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mMobile = intent.getStringExtra(Constants.EXTRA_KEY);
            mVerifyCode = intent.getStringExtra(Constants.EXTRA_KEY2);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_complete:
                mPresenter.setPassword(mMobile, mVerifyCode, StringUtil.textview2String(etPwd), StringUtil.textview2String(etRepwd));
                break;
        }
    }

    @Override
    public void success(String msg) {
        ShowToast.Short(msg);
        AppManager.getAppManager().finishActivity(ForgetPwdActivity.class);
        back();

    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.hide();
    }
}
