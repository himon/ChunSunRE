package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/24.
 */
public interface SettingMode {

    void logout(String token, BaseSingleLoadedListener listener);
}
