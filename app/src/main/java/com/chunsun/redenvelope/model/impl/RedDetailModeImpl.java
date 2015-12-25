package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;


/**
 * Created by Administrator on 2015/8/10.
 */
public class RedDetailModeImpl implements RedDetailMode {

    private RedDetailActivity mActivity;
    private HttpManager mManager;

    public RedDetailModeImpl(RedDetailActivity redDetailActivity) {
        this.mActivity = redDetailActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getRedData(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }

    @Override
    public void getShareLimit(final String token, final UserLoseMultiLoadedListener listener) {
        mManager.getShareLimit(token, listener, mActivity);
    }
}
