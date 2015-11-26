package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.LoginMode;
import com.chunsun.redenvelope.ui.activity.account.LoginActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;


/**
 * Created by Administrator on 2015/7/28.
 */
public class LoginModeImpl implements LoginMode {

    private LoginActivity mActivity;
    private HttpManager mManager;

    public LoginModeImpl(LoginActivity loginActivity) {
        this.mActivity = loginActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void login(final String mobile_or_chunsun, final String password, final String push_device_token, final String json_str, final BaseSingleLoadedListener listener) {
        mManager.login(mobile_or_chunsun, password, push_device_token, json_str, listener, mActivity);
    }
}
