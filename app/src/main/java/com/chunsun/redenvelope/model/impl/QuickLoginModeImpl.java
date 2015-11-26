package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.QuickLoginMode;
import com.chunsun.redenvelope.ui.activity.account.QuickLoginActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/1.
 */
public class QuickLoginModeImpl implements QuickLoginMode {

    private QuickLoginActivity mActivity;
    private HttpManager mManager;

    public QuickLoginModeImpl(QuickLoginActivity quickLoginView) {
        this.mActivity = quickLoginView;
        this.mManager = new HttpManager();
    }

    @Override
    public void getCode(final String mobile, final BaseMultiLoadedListener listener) {
        mManager.getCode(mobile, listener, mActivity);
    }

    @Override
    public void quickLogin(final String phone, final String code,
                           final String phoneInfo, final String pushDeviceToken, final BaseMultiLoadedListener listener) {
        mManager.quickLogin(phone, code, phoneInfo, pushDeviceToken, listener, mActivity);
    }
}
