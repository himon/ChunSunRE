package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.RegisterMode;
import com.chunsun.redenvelope.ui.activity.account.RegisterActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/7/29.
 */
public class RegisterModeImpl implements RegisterMode {

    private RegisterActivity mActivity;
    private HttpManager mManager;

    public RegisterModeImpl(RegisterActivity registerView) {
        this.mActivity = registerView;
        this.mManager = new HttpManager();
    }

    /**
     * 获取验证码
     *
     * @param mobile
     * @param listener
     */
    @Override
    public void registerGetCode(final String mobile, final BaseMultiLoadedListener listener) {
        mManager.registerGetCode(mobile, listener, mActivity);
    }

    /**
     * 验证验证码
     *
     * @param mobile
     * @param verify_code
     * @param listener
     */
    @Override
    public void nextStep(final String mobile, final String verify_code, final BaseMultiLoadedListener listener) {
        mManager.nextStep(mobile, verify_code, listener, mActivity);
    }

    /**
     * 验证邀请码
     *
     * @param invitation_code
     * @param listener
     */
    @Override
    public void hasInviteCodeNextStep(final String invitation_code, final BaseMultiLoadedListener listener) {
        mManager.hasInviteCodeNextStep(invitation_code, listener, mActivity);
    }
}
