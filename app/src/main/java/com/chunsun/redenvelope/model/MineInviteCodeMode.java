package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/22.
 */
public interface MineInviteCodeMode {

    void getInviteRecord(String token, UserLoseMultiLoadedListener listener);

    /**
     * 获取用户个人信息
     */
    void getUserInfomation(String token, UserLoseMultiLoadedListener listener);
}
