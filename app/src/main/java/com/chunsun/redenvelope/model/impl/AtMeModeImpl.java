package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.AtMeMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * @author Administrator
 * @version $Rev$
 * @time 2016/1/19 18:05
 * @des
 */
public class AtMeModeImpl extends BaseModeImpl implements AtMeMode{

    private Activity mActivity;

    public AtMeModeImpl(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void getUserNoReadMessage(String token, int page_index, UserLoseMultiLoadedListener listener) {
        mManager.getUserNoReadMessage(token, page_index, listener, mActivity);
    }

    @Override
    public void userReadMessage(String token, int type, UserLoseMultiLoadedListener listener) {
        mManager.userReadMessage(token, type, listener, mActivity);
    }
}
