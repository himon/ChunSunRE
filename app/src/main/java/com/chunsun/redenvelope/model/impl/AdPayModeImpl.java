package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.AdPayMode;
import com.chunsun.redenvelope.ui.activity.ad.AdPayActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/8.
 */
public class AdPayModeImpl implements AdPayMode {

    private AdPayActivity mActivity;
    private HttpManager mManager;

    public AdPayModeImpl(AdPayActivity adPayActivity) {
        this.mActivity = adPayActivity;
        this.mManager = new HttpManager();
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
