package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface UserPrivacyMode {

    /**
     * 修改用户隐私设置
     *
     * @param token
     * @param private_json
     * @param listener
     */
    void updateUserPrivacySetting(String token, String private_json, BaseSingleLoadedListener listener);
}
