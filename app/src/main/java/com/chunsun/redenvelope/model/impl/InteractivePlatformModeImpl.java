package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.InteractivePlatformMode;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * 互动平台Mode
 */
public class InteractivePlatformModeImpl implements InteractivePlatformMode {

    private InteractivePlatformActivity mActivity;
    private HttpManager mManager;

    public InteractivePlatformModeImpl(InteractivePlatformActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void getCountryCommentList(final String token, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getCountryCommentList(token, page_index, listener, mActivity);
    }

    @Override
    public void getLocalCommentList(final String token, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.getLocalCommentList(token, page_index, listener, mActivity);
    }

    @Override
    public void sendComment(final String token, final String comment, final String province, final String city, final BaseMultiLoadedListener listener) {
        mManager.sendComment(token, comment, province, city, listener, mActivity);
    }
}
