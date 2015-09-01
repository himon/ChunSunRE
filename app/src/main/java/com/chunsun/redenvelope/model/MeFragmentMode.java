package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/3.
 */
public interface MeFragmentMode {

    /**
     * 获取用户个人信息
     */
    void getUserInfomation(final String token, final BaseSingleLoadedListener listener);
}
