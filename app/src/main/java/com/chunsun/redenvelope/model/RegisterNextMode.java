package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/7/30.
 */
public interface RegisterNextMode {

    /**
     * 注册
     *
     * @param type
     * @param mobile
     * @param verify_code
     * @param password
     * @param confirm_pwd
     * @param push_device_token
     * @param json_str
     * @param invitation_code
     * @param listener
     */
    void register(String type, String mobile, String verify_code,
                  String password, String confirm_pwd, String push_device_token,
                  String json_str, String invitation_code, BaseMultiLoadedListener listener);

    /**
     * 获取用户信息
     *
     * @param token
     * @param listener
     */
    void getUserInfo(String token, UserLoseMultiLoadedListener listener);
}
