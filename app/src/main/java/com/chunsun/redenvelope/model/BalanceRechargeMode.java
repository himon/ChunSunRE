package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface BalanceRechargeMode {

    /**
     * 余额充值
     *
     * @param token
     * @param type
     * @param amount
     * @param listener
     */
    void balanceRecharge(String token, String type, String amount, BaseSingleLoadedListener listener);
}
