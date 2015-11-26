package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.ForgetPwdMode;
import com.chunsun.redenvelope.ui.activity.account.ForgetPwdActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/3.
 */
public class ForgetPwdModeImpl implements ForgetPwdMode {

    private ForgetPwdActivity mActivity;
    private HttpManager mManager;

    public ForgetPwdModeImpl(ForgetPwdActivity view) {
        this.mActivity = view;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCode(final String mobile, final BaseMultiLoadedListener listener) {
        mManager.getCode(mobile, listener, mActivity);
    }

    @Override
    public void nextStep(final String mobile, final String verify_code, final BaseMultiLoadedListener listener) {
        mManager.nextStep(mobile, verify_code, listener, mActivity);
    }
}
