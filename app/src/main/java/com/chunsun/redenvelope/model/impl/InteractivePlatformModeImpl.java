package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.InteractivePlatformMode;
import com.chunsun.redenvelope.ui.activity.InteractivePlatformActivity;
import com.chunsun.redenvelope.ui.fragment.tab.InteractiveFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * 互动平台Mode
 */
public class InteractivePlatformModeImpl implements InteractivePlatformMode {

    private InteractivePlatformActivity mActivity;
    private InteractiveFragment mFragment;
    private HttpManager mManager;

    public InteractivePlatformModeImpl(InteractivePlatformActivity activity, InteractiveFragment fragment) {
        mActivity = activity;
        mFragment = fragment;
        mManager = new HttpManager();
    }

    @Override
    public void getCountryCommentList(final String token, final int page_index, final UserLoseMultiLoadedListener listener) {
        mManager.getCountryCommentList(token, page_index, listener, mActivity, mFragment);
    }

    @Override
    public void getLocalCommentList(final String token, final int page_index, final UserLoseMultiLoadedListener listener) {
        mManager.getLocalCommentList(token, page_index, listener, mActivity, mFragment);
    }

    @Override
    public void sendComment(final String token, final String comment, final String province, final String city, String at, final UserLoseMultiLoadedListener listener) {
        mManager.sendComment(token, comment, province, city, at, listener, mActivity);
    }
}
