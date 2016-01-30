package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RegisterNextMode;
import com.chunsun.redenvelope.ui.activity.account.RegisterNextActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/7/30.
 */
public class RegisterNextModeImpl implements RegisterNextMode {

    private RegisterNextActivity mActivity;
    private HttpManager mManager;

    public RegisterNextModeImpl(RegisterNextActivity registerNextView) {
        this.mActivity = registerNextView;
        this.mManager = new HttpManager();
    }

    @Override
    public void register(final String type, final String mobile, final String verify_code, final String password, final String confirm_pwd, final String push_device_token, final String json_str, final String invitation_code, final BaseMultiLoadedListener listener) {
        mManager.register(type, mobile, verify_code, password, confirm_pwd, push_device_token, json_str, invitation_code, listener, mActivity);
    }

    @Override
    public void getUserInfo(final String token, final UserLoseMultiLoadedListener listener) {
        mManager.getUserInfomation(token, listener, null, mActivity);
    }
}
