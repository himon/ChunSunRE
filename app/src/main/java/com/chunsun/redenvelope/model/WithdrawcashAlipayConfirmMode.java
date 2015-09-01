package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;

/**
 * Created by Administrator on 2015/8/20.
 */
public interface WithdrawcashAlipayConfirmMode {

    void rechargeByAlipay(String token, String zfb_no, String zfb_name,
                          int zfb_poundage_id, BaseSingleLoadedListener listener);
}
