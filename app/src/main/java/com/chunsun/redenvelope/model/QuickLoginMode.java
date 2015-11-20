package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/1.
 */
public interface QuickLoginMode {

    /**
     * 获取验证码
     */
    void getCode(String mobile, BaseMultiLoadedListener listener);


    /**
     * 验证验证码
     *
     * @param phone
     * @param code
     * @param phoneInfo
     * @param pushDeviceToken
     * @param listener
     */
    void quickLogin(String phone, String code,
               String phoneInfo, String pushDeviceToken, BaseMultiLoadedListener listener);
}
