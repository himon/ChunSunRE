package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface UserRewardMode {

    void getData(String token, String user_id, BaseMultiLoadedListener listener);
}
