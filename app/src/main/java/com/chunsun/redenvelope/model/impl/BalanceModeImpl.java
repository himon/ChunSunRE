package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.model.BalanceMode;
import com.chunsun.redenvelope.ui.activity.personal.WalletActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/18.
 */
public class BalanceModeImpl implements BalanceMode {

    private WalletActivity mActivity;
    private HttpManager mManager;

    public BalanceModeImpl(WalletActivity balanceActivity) {
        this.mActivity = balanceActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadWalletData(final String token, final BaseMultiLoadedListenerImpl listener) {
        mManager.loadWalletData(token, listener, mActivity);
    }
}
