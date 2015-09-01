package com.chunsun.redenvelope.presenter.impl;

import android.text.TextUtils;

import com.chunsun.redenvelope.app.MainApplication;
import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.LoginMode;
import com.chunsun.redenvelope.model.entity.json.SampleResponseEntity;
import com.chunsun.redenvelope.model.impl.LoginModeImpl;
import com.chunsun.redenvelope.preference.Preferences;
import com.chunsun.redenvelope.presenter.OnLoginFinishedListener;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.ui.view.ILoginView;
import com.chunsun.redenvelope.utils.ShowToast;

/**
 * Created by Administrator on 2015/7/28.
 */
public class LoginPresenter implements BaseSingleLoadedListener<SampleResponseEntity> {

    private ILoginView loginView;
    private LoginMode loginMode;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        loginMode = new LoginModeImpl((LoginActivity) loginView);
    }

    /**
     * 跳转注册Activity
     */
    public void register() {
        loginView.toRegisterActivity();
    }

    /**
     * 跳转忘记密码Activity
     */
    public void findPwd() {
        loginView.toForgetPwdActivity();
    }

    /**
     * 跳转快捷登录Activity
     */
    public void quickLogin() {
        loginView.toQuickLoginActivity();
    }

    /**
     * 验证用户名
     *
     * @param account
     * @return
     */
    public boolean valiAccount(String account) {

        if (TextUtils.isEmpty(account)) {
            return false;
        }
        return true;
    }

    /**
     * 验证密码
     *
     * @param pwd
     * @return
     */
    public boolean valiPwd(String pwd) {
        if (TextUtils.isEmpty(pwd)) {
            return false;
        }
        return true;
    }

    /**
     * 登录操作
     *
     * @param mobile_or_chunsun
     * @param password
     * @param push_device_token
     * @param json_str
     */
    public void login(String mobile_or_chunsun,
                      String password, String push_device_token, String json_str) {

        if (valiAccount(mobile_or_chunsun) && valiPwd(password)) {
            loginView.showLoading();
            loginMode.login(mobile_or_chunsun,
                    password, push_device_token, json_str, this);
        }
    }

    @Override
    public void onSuccess(SampleResponseEntity entity) {
        loginView.hideLoading();
        new Preferences(MainApplication.getContext()).setToken(entity.getResult());
        loginView.success();
    }

    @Override
    public void onError(String msg) {
        loginView.hideLoading();
        ShowToast.Short(msg);
    }

    @Override
    public void onException(String msg) {
        loginView.hideLoading();
        ShowToast.Short(msg);
    }
}
