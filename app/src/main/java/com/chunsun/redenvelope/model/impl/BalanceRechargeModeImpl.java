package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.BalanceRechargeMode;
import com.chunsun.redenvelope.ui.activity.personal.BalanceRechargeActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/21.
 */
public class BalanceRechargeModeImpl implements BalanceRechargeMode {

    private BalanceRechargeActivity mActivity;
    private HttpManager mManager;

    public BalanceRechargeModeImpl(BalanceRechargeActivity balanceRechargeActivity) {
        this.mActivity = balanceRechargeActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void balanceRecharge(final String token, final String type, final String amount, final BaseSingleLoadedListener listener) {
        mManager.balanceRecharge(token, type, amount, listener, mActivity);
    }
}
