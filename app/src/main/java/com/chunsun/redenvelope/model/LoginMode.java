package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/7/28.
 */
public interface LoginMode {

    void login(String mobile_or_chunsun, String password, String push_device_token, String json_str, BaseSingleLoadedListener listener);
}
