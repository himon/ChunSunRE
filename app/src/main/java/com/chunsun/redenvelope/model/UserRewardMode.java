package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;

/**
 * Created by Administrator on 2015/9/15.
 */
public interface UserRewardMode {

    /**
     * 获取用户公开信息
     *
     * @param token
     * @param user_id
     * @param listener
     */
    void getUserPublicData(String token, String user_id, BaseMultiLoadedListener listener);

    /**
     * 获取用户账户信息
     *
     * @param token
     * @param listener
     */
    void getUserAmount(String token, BaseMultiLoadedListener listener);

    /**
     * 支付奖励
     *
     * @param token
     * @param user_id
     * @param amount
     * @param msg
     * @param hb_id
     * @param listener
     */
    void transfer(String token, String user_id, String amount, String msg, String hb_id, String province, String city, BaseMultiLoadedListener listener);
}
