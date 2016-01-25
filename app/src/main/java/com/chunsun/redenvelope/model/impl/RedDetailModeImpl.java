package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.RedDetailMode;
import com.chunsun.redenvelope.ui.activity.red.RedDetailActivity;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;


/**
 * Created by Administrator on 2015/8/10.
 */
public class RedDetailModeImpl extends BaseModeImpl implements RedDetailMode {

    private Activity mActivity;

    public RedDetailModeImpl(RedDetailActivity redDetailActivity) {
        this.mActivity = redDetailActivity;
    }

    @Override
    public void getRedData(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.getRedData(token, hb_id, listener, mActivity);
    }
}
