package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface UserPrivacyMode {

    void updateUserInfo(String token, String private_json, BaseSingleLoadedListener listener);
}
