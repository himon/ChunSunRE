package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/21.
 */
public interface BalanceRechargeMode {

    void recharge(String token, String type, String amount, BaseSingleLoadedListener listener);
}
