package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.WithdrawcashAlipayConfirmMode;
import com.chunsun.redenvelope.ui.activity.personal.WithdrawcashAlipayConfirmActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/20.
 */
public class WithdrawcashAlipayConfirmModeImpl implements WithdrawcashAlipayConfirmMode {

    private WithdrawcashAlipayConfirmActivity mActivity;
    private HttpManager mManager;

    public WithdrawcashAlipayConfirmModeImpl(WithdrawcashAlipayConfirmActivity withdrawcashAlipayConfirmActivity) {
        this.mActivity = withdrawcashAlipayConfirmActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void rechargeByAlipay(final String token, final String zfb_no, final String zfb_name, final int zfb_poundage_id, final BaseSingleLoadedListener listener) {
        mManager.rechargeByAlipay(token, zfb_no, zfb_name, zfb_poundage_id, listener, mActivity);
    }
}
