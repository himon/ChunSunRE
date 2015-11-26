package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdNextMode;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdNextActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ForgetPwdNextModeImpl implements ForgetPwdNextMode {

    private ForgetPwdNextActivity mActivity;
    private HttpManager mManager;

    public ForgetPwdNextModeImpl(ForgetPwdNextActivity mActivity) {
        this.mActivity = mActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void findPwdSubmit(final String mobile, final String verify_code,
                              final String new_pwd, final String confirm_pwd, final BaseSingleLoadedListener listener) {
        mManager.findPwdSubmit(mobile, verify_code, new_pwd, confirm_pwd, listener, mActivity);
    }
}
