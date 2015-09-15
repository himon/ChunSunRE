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
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.model.event.MainEvent;
import com.chunsun.redenvelope.model.event.ValiCodeEvent;
import com.chunsun.redenvelope.presenter.impl.QuickLoginPresenter;
import com.chunsun.redenvelope.ui.activity.CommonWebActivity;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.base.BaseActivity;
import com.chunsun.redenvelope.ui.view.IQuickLoginView;
import com.chunsun.redenvelope.utils.CountDownUtils;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 快捷登录Activity
 */
public class QuickLoginActivity extends BaseActivity implements IQuickLoginView, View.OnClickListener, TextWatcher {

    @Bind(R.id.et_input_phone)
    EditText etPhoneNum;
    @Bind(R.id.et_input_code)
    EditText etValiCode;
    @Bind(R.id.btn_get_code)
    Button btnGetCode;
    @Bind(R.id.cb_agree_service_protocol)
    CheckBox cbAgree;
    @Bind(R.id.tv_service_protocol)
    TextView tvServiceProtocol;
    @Bind(R.id.btn_login)
    Button btnLogin;

    //标示是否以点击获取验证码
    private boolean countdowning = false;
    private QuickLoginPresenter mPresenter;
    private String mFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_login);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mPresenter = new QuickLoginPresenter(this);
        initView();
        initData();
    }

    @Override
    protected void initView() {
        initTitleBar("快捷登录", "返回", "", Constants.TITLE_TYPE_SAMPLE);

        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        btnGetCode.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        tvServiceProtocol.setOnClickListener(this);

        etValiCode.addTextChangedListener(this);
        etPhoneNum.addTextChangedListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mFrom = intent.getStringExtra(Constants.EXTRA_KEY);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
                back();
                break;
            case R.id.tv_service_protocol:
                showServiceProtocol();
                break;
            case R.id.btn_get_code:
                mPresenter.getValiCode(StringUtil.textview2String(etPhoneNum));
                btnGetCode.setEnabled(false);
                countdowning = true;
                break;
            case R.id.btn_login:
                mPresenter.login(StringUtil.textview2String(etPhoneNum), StringUtil.textview2String(etValiCode));
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!countdowning) {
            CountDownUtils.getCodeIsEnable(StringUtil.textview2String(etPhoneNum), btnGetCode, getResources());
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

        if (!TextUtils.isEmpty(StringUtil.textview2String(etPhoneNum)) && !TextUtils.isEmpty(StringUtil.textview2String(etValiCode)) && cbAgree.isChecked()) {
            btnLogin.setEnabled(true);
        } else {
            btnLogin.setEnabled(false);
        }
    }

    @Override
    public void success() {
        if (mFrom.equals(Constants.FROM_AD)) {
            EventBus.getDefault().post(new MainEvent(Constants.FROM_AD));
        } else if (mFrom.equals(Constants.FROM_ME)) {
            EventBus.getDefault().post(new MainEvent(Constants.FROM_ME));
        }
        Intent intent = new Intent(this, MainActivity.class);
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
