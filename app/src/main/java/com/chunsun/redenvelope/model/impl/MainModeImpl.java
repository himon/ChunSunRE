package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MainMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/21 16:41
 * @des
 */
public class MainModeImpl extends BaseModeImpl implements MainMode {

    private Activity mActivity;

    public MainModeImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void getUserNoReadCount(String token, BaseMultiLoadedListener listener) {
        mManager.getUserNoReadCount(token, listener, mActivity);
    }
}
