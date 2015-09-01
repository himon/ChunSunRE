package com.chunsun.redenvelope.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.event.ValiCodeEvent;
import com.chunsun.redenvelope.presenter.impl.ForgetPwdPresenter;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IForgetPwdView;
import com.chunsun.redenvelope.utils.CountDownUtil;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class ForgetPwdActivity extends BaseActivity implements IForgetPwdView, View.OnClickListener, TextWatcher {

    @Bind(R.id.et_input_phone)
    EditText etPhoneNum;
    @Bind(R.id.et_input_code)
    EditText etCode;
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Bind(R.id.btn_next_step)
    Button btnNextStep;

    //标示是否以点击获取验证码
    private boolean countdowning = false;
    private ForgetPwdPresenter mPresenter;

    private String mMobile;
    private String mVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pwd);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new ForgetPwdPresenter(this);
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
        btnGetCode.setOnClickListener(this);
        btnNextStep.setOnClickListener(this);

        etPhoneNum.addTextChangedListener(this);
        etCode.addTextChangedListener(this);
    }

    @Override
    protected void initData() {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_get_code:
                mPresenter.getValiCode(StringUtil.textview2String(etPhoneNum));
                btnGetCode.setEnabled(false);
                countdowning = true;
                break;
            case R.id.btn_next_step:
                mMobile = StringUtil.textview2String(etPhoneNum);
                mVerifyCode = StringUtil.textview2String(etCode);
                mPresenter.nextStep(mMobile, mVerifyCode);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!countdowning) {
            CountDownUtil.getCodeIsEnable(StringUtil.textview2String(etPhoneNum), btnGetCode, getResources());
        }
        isNextStep();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    /**
     * 判断是否可以点击下一步
     */
    private void isNextStep() {

        if (!TextUtils.isEmpty(StringUtil.textview2String(etPhoneNum)) && !TextUtils.isEmpty(StringUtil.textview2String(etCode))) {
            btnNextStep.setEnabled(true);
        } else {
            btnNextStep.setEnabled(false);
        }
    }

    @Override
    public void nextStep() {
        Intent intent = new Intent(this, ForgetPwdNextActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mMobile);
        intent.putExtra(Constants.EXTRA_KEY2, mVerifyCode);
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        mDialog.show();
    }

    @Override
    public void hideLoading() {
        mDialog.dismiss();
    }

    public void onEventMainThread(ValiCodeEvent event) {
        if ("获取验证码".equals(event.getMsg())) {
            btnGetCode.setEnabled(true);
            countdowning = false;
        }
        btnGetCode.setText(event.getMsg());
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
