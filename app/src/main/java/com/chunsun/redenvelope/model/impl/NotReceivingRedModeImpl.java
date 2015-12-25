package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.NotReceivingRedMode;
import com.chunsun.redenvelope.ui.activity.personal.NotReceivingRedActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/15.
 */
public class NotReceivingRedModeImpl implements NotReceivingRedMode {

    private NotReceivingRedActivity mActivity;
    private HttpManager mManager;

    public NotReceivingRedModeImpl(NotReceivingRedActivity activity) {
        this.mActivity = activity;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadData(final String token, final UserLoseMultiLoadedListener listener) {
        mManager.loadData(token, listener, mActivity);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.grabRedEnvelope(token, hb_id, listener, null, mActivity);
    }
}
