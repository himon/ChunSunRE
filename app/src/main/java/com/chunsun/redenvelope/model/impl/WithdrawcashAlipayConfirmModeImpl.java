package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.WithdrawcashAlipayConfirmMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * Created by Administrator on 2015/8/20.
 */
public class WithdrawcashAlipayConfirmModeImpl extends BaseModeImpl implements WithdrawcashAlipayConfirmMode {

    private Activity mActivity;

    public WithdrawcashAlipayConfirmModeImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void rechargeByAlipay(final String token, final String zfb_no, final String zfb_name, final int zfb_poundage_id, final UserLoseMultiLoadedListener listener) {
        mManager.rechargeByAlipay(token, zfb_no, zfb_name, zfb_poundage_id, listener, mActivity);
    }

    @Override
    public void rechargeByBank(String token, String name, String bank_name, String bank_no, String open_bank_name, String province, String city, String rate_id, UserLoseMultiLoadedListener listener) {
        mManager.rechargeByBank(token, name, bank_name, bank_no, open_bank_name, province, city, rate_id, listener, mActivity);
    }
}
