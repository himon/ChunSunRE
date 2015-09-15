package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface UserInfoMode {

    /**
     * 修改用户基本信息
     *
     * @param token
     * @param field_name
     * @param field_value
     * @param listener
     */
    void editUserInfo(String token, String field_name, String field_value, BaseMultiLoadedListener listener);
}
