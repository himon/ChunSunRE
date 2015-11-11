package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface MineInviteCodeMode {

    void getInviteRecord(String token, BaseMultiLoadedListener listener);

    /**
     * 获取用户个人信息
     */
    void getUserInfomation(String token, BaseMultiLoadedListener listener);
}
