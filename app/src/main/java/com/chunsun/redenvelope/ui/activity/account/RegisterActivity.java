package com.chunsun.redenvelope.ui.activity.account;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.model.event.ValiCodeEvent;
import com.chunsun.redenvelope.presenter.impl.RegisterPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.ui.view.IRegisterView;
import com.chunsun.redenvelope.utils.ShowToast;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 注册Activity
 */
public class RegisterActivity extends BaseActivity implements IRegisterView, View.OnClickListener, TextWatcher {

    @Bind(R.id.et_input_phone)
    EditText mPhoneNum;
    @Bind(R.id.et_input_invite_code)
    EditText mInviteCode;
    @Bind(R.id.et_input_code)
    EditText mInValiCode;
    @Bind(R.id.tv_get_code)
    TextView mGetCode;
    @Bind(R.id.cb_agree_service_protocol)
    CheckBox mAgree;
    @Bind(R.id.btn_next_step)
    Button mNext;
    @Bind(R.id.btn_personal)
    Button mPersonalAccount;
    @Bind(R.id.btn_enterprise)
    Button mCompanyAccount;
    @Bind(R.id.view_personal)
    View mPersonalLine;
    @Bind(R.id.view_enterprise)
    View mCompanyLine;
    @Bind(R.id.tv_service_protocol)
    TextView mServiceProtocol;


    private RegisterPresenter mPresenter;
    //标示是否以点击获取验证码
    private boolean countdowning = false;
    //注册类型：1 个人，2 企业
    private String mTypeId = Constants.REGISTER_TYPE_PERSONAL;
    //手机号
    private String mPhone;
    //验证码
    private String mCode;
    //邀请码
    private String mNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new RegisterPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {

        initTitleBar("注 册", "", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();

    }

    @Override
    protected void initData() {

    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mPersonalAccount.setOnClickListener(this);
        mCompanyAccount.setOnClickListener(this);
        mGetCode.setOnClickListener(this);
        mNext.setOnClickListener(this);
        mServiceProtocol.setOnClickListener(this);

        mPhoneNum.addTextChangedListener(this);
        mInValiCode.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.btn_personal:
                mPersonalLine.setBackgroundColor(getResources().getColor(R.color.global_red));
                mCompanyLine.setBackgroundColor(getResources().getColor(R.color.white));
                mTypeId = Constants.REGISTER_TYPE_PERSONAL;
                break;
            case R.id.btn_enterprise:
                mPersonalLine.setBackgroundColor(getResources().getColor(R.color.white));
                mCompanyLine.setBackgroundColor(getResources().getColor(R.color.global_red));
                mTypeId = Constants.REGISTER_TYPE_COMPANY;
                break;
            case R.id.tv_get_code:
                mPresenter.getValiCode(StringUtil.textview2String(mPhoneNum));
                mGetCode.setEnabled(false);
                countdowning = true;
                break;
            case R.id.btn_next_step:
                mPhone = StringUtil.textview2String(mPhoneNum);
                mCode = StringUtil.textview2String(mInValiCode);
                mNum = StringUtil.textview2String(mInviteCode);
                mPresenter.next(mPhone, mCode, mNum);
                break;
            case R.id.tv_service_protocol:
                showServiceProtocol();
                break;
        }
    }

    @Override
    public void success() {
        Intent intent = new Intent(this, RegisterNextActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mTypeId);
        intent.putExtra(Constants.EXTRA_KEY2, mPhone);
        intent.putExtra(Constants.EXTRA_KEY3, mCode);
        intent.putExtra(Constants.EXTRA_KEY4, mNum);
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

    @Override
    public void showServiceProtocol() {
        Intent intent = new Intent(this, CommonWebActivity.class);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_URL, Constants.REGISTER_SERVICE_PROTOCOL);
        intent.putExtra(Constants.INTENT_BUNDLE_KEY_COMMON_WEB_VIEW_TITLE, "服务协议");
        startActivity(intent);
    }

    @Override
    public void phoneNumError() {
        ShowToast.Short("请输入正确的手机号码！");
    }


    /**
     * 监听EditText输入事件
     *
     * @param s
     * @param start
     * @param count
     * @param after
     */
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!countdowning) {
            getCodeIsEnable(StringUtil.textview2String(mPhoneNum));
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

        if (!TextUtils.isEmpty(StringUtil.textview2String(mPhoneNum)) && !TextUtils.isEmpty(StringUtil.textview2String(mInValiCode)) && mAgree.isChecked()) {
            mNext.setEnabled(true);
        } else {
            mNext.setEnabled(false);
        }
    }

    /**
     * 验证是否激活获取验证码按钮
     */
    private void getCodeIsEnable(String phoneNum) {

        if (StringUtil.isPhoneNum(phoneNum)) {
            mGetCode.setEnabled(true);
            mGetCode.setTextColor(getResources()
                    .getColor(R.color.font_blue));
        } else {
            mGetCode.setEnabled(false);
            mGetCode.setTextColor(getResources().getColor(
                    R.color.font_hint_gray));
        }
    }


    public void onEventMainThread(ValiCodeEvent event) {
        if ("获取验证码".equals(event.getMsg())) {
            mGetCode.setEnabled(true);
            countdowning = false;
        }
        mGetCode.setText(event.getMsg());
    }

    @Override
    protected void onDestroy() {
        //取消注册EventBus
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
