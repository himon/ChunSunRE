package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/7/29.
 */
public interface RegisterMode {

    /**
     * 获取验证码
     */
    void registerGetCode(String mobile, BaseMultiLoadedListener listener);

    /**
     * 验证验证码
     *
     * @param mobile
     * @param verify_code
     * @param listener
     */
    void nextStep(String mobile, String verify_code, BaseMultiLoadedListener listener);

    /**
     * 验证邀请码
     *
     * @param invitation_code
     * @param listener
     */
    void hasInviteCodeNextStep(String invitation_code, final BaseMultiLoadedListener listener);
}
