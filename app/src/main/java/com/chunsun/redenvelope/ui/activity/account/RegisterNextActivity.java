package com.chunsun.redenvelope.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.presenter.RegisterNextPresenter;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IRegisterNextView;
import com.chunsun.redenvelope.utils.StringUtil;
import com.chunsun.redenvelope.widget.TextButtonDialog;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注册输入密码Activity
 */
public class RegisterNextActivity extends BaseActivity implements IRegisterNextView, View.OnClickListener {

    @Bind(R.id.et_input_password)
    EditText mPwd;
    @Bind(R.id.et_confirm_password)
    EditText mRepwd;
    @Bind(R.id.btn_register)
    Button mRegister;

    private String mTypeId;
    private String mPhone;
    private String mCode;
    private String mInviteCode;

    private RegisterNextPresenter mPresenter;
    private TextButtonDialog mAlertDialog;

    private boolean isSuccess = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_next);
        ButterKnife.bind(this);
        mPresenter = new RegisterNextPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        initTitleBar("注 册", "", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mRegister.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        Intent intent = getIntent();
        if (intent != null) {
            mTypeId = intent.getStringExtra(Constants.EXTRA_KEY);
            mPhone = intent.getStringExtra(Constants.EXTRA_KEY2);
            mCode = intent.getStringExtra(Constants.EXTRA_KEY3);
            mInviteCode = intent.getStringExtra(Constants.EXTRA_KEY4);
        }
    }


    @Override
    public void success() {

    }

    @Override
    public void showLoading() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void successShowDialog(String content) {
        isSuccess = true;
        mAlertDialog = new TextButtonDialog(this, R.style.progress_dialog, this);
        mAlertDialog.show();
        if (TextUtils.isEmpty(mInviteCode)) {
            mAlertDialog.setDialogContent(content);
        } else {
            mAlertDialog.setDialogContent(content + "，奖励1元，进入个人中心余额查看");
        }
        mAlertDialog.diyGetRedDialog();
    }

    @Override
    public void errorShowDialog(String content) {
        isSuccess = false;
        mAlertDialog = new TextButtonDialog(this, R.style.progress_dialog, this);
        mAlertDialog.show();
        mAlertDialog.setDialogContent(content);
        mAlertDialog.singleButtonDialog();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_register:
                mPresenter.register(mTypeId, mPhone, mCode, StringUtil.textview2String(mPwd), StringUtil.textview2String(mRepwd), "asfasfsadfsadfsadfasdf", MainApplication.getContext().getPhoneInfomation(), mInviteCode);
                break;
            case R.id.btn_confirm_ok:
                break;
            case R.id.btn_confirm_cancel:
                mAlertDialog.dismiss();
                if(isSuccess){
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                    back();
                }
                break;
        }
    }
}
