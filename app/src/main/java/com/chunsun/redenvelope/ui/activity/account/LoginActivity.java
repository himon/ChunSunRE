package com.chunsun.redenvelope.ui.activity.account;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chunsun.redenvelope.R;
import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.app.context.LoginContext;
import com.chunsun.redenvelope.app.state.impl.LoginState;
import com.chunsun.redenvelope.constants.Constants;
import com.chunsun.redenvelope.event.MainEvent;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.LoginPresenter;
import com.chunsun.redenvelope.ui.activity.MainActivity;
import com.chunsun.redenvelope.ui.base.activity.MBaseActivity;
import com.chunsun.redenvelope.ui.base.presenter.BasePresenter;
import com.chunsun.redenvelope.ui.view.ILoginView;
import com.chunsun.redenvelope.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * 登录Activity
 */
public class LoginActivity extends MBaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    @Bind(R.id.tv_no_pwd_login)
    TextView mQuickLogin;
    @Bind(R.id.tv_forget_pwd)
    TextView mForgetPwd;
    @Bind(R.id.btn_login)
    Button mLogin;
    @Bind(R.id.et_input_account)
    EditText mAccount;
    @Bind(R.id.et_input_password)
    EditText mPwd;

    private LoginPresenter mPresenter;
    //标示从哪跳转到登录页面
    private String mFrom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = (LoginPresenter) super.mMPresenter;
        initView();
        initData();
    }

    @Override
    protected BasePresenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void initView() {
        initTitleBar("登 录", "返回", "注册", Constants.TITLE_TYPE_SAMPLE_NO_BACK);
        initEvent();
    }

    private void initEvent() {
        mNavLeft.setOnClickListener(this);
        mNavRight.setOnClickListener(this);
        mQuickLogin.setOnClickListener(this);
        mForgetPwd.setOnClickListener(this);
        mLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent != null) {
            mFrom = intent.getStringExtra(Constants.EXTRA_KEY);
        }
    }

    @Override
    protected void click(View v) {
        switch (v.getId()) {
            case R.id.tv_nav_left:
            case R.id.iv_nav_icon:
                EventBus.getDefault().post(new MainEvent(Constants.FROM_LOGIN_BACK));
                back();
                break;
            case R.id.tv_nav_right:
                mPresenter.register();
                break;
            case R.id.tv_no_pwd_login:
                mPresenter.quickLogin();
                break;
            case R.id.tv_forget_pwd:
                mPresenter.findPwd();
                break;
            case R.id.btn_login:
                String xgToken = new Preferences(this).getXGToken();
                mPresenter.login(StringUtil.textview2String(mAccount), StringUtil.textview2String(mPwd), xgToken, MainApplication.getContext().getPhoneInfomation());
                break;
        }
    }

    @Override
    public void toRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    @Override
    public void toQuickLoginActivity() {
        Intent intent = new Intent(this, QuickLoginActivity.class);
        intent.putExtra(Constants.EXTRA_KEY, mFrom);
        startActivity(intent);
    }

    @Override
    public void toForgetPwdActivity() {
        Intent intent = new Intent(this, ForgetPwdActivity.class);
        startActivity(intent);
    }

    @Override
    public void success() {
        //更改UserState为登录状态
        LoginContext.getLoginContext().setState(new LoginState());
        if(mFrom == null){
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            back();
            return;
        }

        if (mFrom.equals(Constants.FROM_AD)) {
            EventBus.getDefault().post(new MainEvent(Constants.FROM_AD));
        } else if (mFrom.equals(Constants.FROM_ME)) {
            EventBus.getDefault().post(new MainEvent(Constants.FROM_ME));
        } else if (mFrom.equals(Constants.FROM_TAB1)) {
            EventBus.getDefault().post(new MainEvent(Constants.FROM_TAB1));
        } else if (mFrom.equals(Constants.FROM_TAB3)) {
            EventBus.getDefault().post(new MainEvent(Constants.FROM_TAB3));
        }else if(mFrom.equals(Constants.FROM_COMMENT)){
            EventBus.getDefault().post(new MainEvent(Constants.FROM_COMMENT));
        }
        back();
    }

    @Override
    public void showLoading() {
       super.showCircleLoading();
    }

    @Override
    public void hideLoading() {
        super.hideCircleLoading();
    }

}
