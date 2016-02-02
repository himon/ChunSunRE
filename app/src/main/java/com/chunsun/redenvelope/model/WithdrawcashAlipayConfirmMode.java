package com.chunsun.redenvelope.model;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;

/**
 * Created by Administrator on 2015/8/20.
 */
public interface WithdrawcashAlipayConfirmMode {

    void rechargeByAlipay(String token, String zfb_no, String zfb_name,
                          int zfb_poundage_id, UserLoseMultiLoadedListener listener);

    void rechargeByBank(String token, String name, String bank_name, String bank_no, String open_bank_name, String province, String city, String rate_id, UserLoseMultiLoadedListener listener);
}
