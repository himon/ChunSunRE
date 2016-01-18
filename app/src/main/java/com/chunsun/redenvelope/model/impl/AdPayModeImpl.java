package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.AdPayMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * Created by Administrator on 2015/9/8.
 */
public class AdPayModeImpl extends BaseModeImpl implements AdPayMode {

    private Activity mActivity;

    public AdPayModeImpl(Activity adPayActivity) {
        this.mActivity = adPayActivity;
    }

    /**
     * 获取广告金额信息
     *
     * @param token
     * @param hb_id
     * @param listener
     */
    @Override
    public void getAdAmountDetail(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.getAdAmountDetail(token, hb_id, listener, mActivity);
    }

    @Override
    public void payByBalance(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.payByBalance(token, hb_id, listener, mActivity);
    }
}
