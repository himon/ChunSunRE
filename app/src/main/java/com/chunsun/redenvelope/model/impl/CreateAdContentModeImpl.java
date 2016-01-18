package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.entities.AdEntity;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdContentMode;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdContentModeImpl extends BaseModeImpl implements CreateAdContentMode {

    private CreateAdContentActivity mActivity;

    public CreateAdContentModeImpl(CreateAdContentActivity createAdNextPageActivity) {
        this.mActivity = createAdNextPageActivity;
    }

    @Override
    public void commitAdCreate(final String token, final AdEntity adEntity, final String title, final String content, final UserLoseMultiLoadedListener listener) {
        mManager.commitAdCreate(token, adEntity, title, content, listener, mActivity);
    }

    @Override
    public void commitCircleCreate(String token, AdEntity adEntity, String title, String content, UserLoseMultiLoadedListener listener) {
        mManager.commitCircleCreate(token, adEntity, title, content, listener, mActivity);
    }

    @Override
    public void commitLuckCreate(String token, AdEntity adEntity, String title, String content, UserLoseMultiLoadedListener listener) {
        mManager.commitLuckCreate(token, adEntity, title, content, listener, mActivity);
    }
}
