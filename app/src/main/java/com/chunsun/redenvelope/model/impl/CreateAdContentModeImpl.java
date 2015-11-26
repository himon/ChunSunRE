package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.CreateAdContentMode;
import com.chunsun.redenvelope.model.entity.AdEntity;
import com.chunsun.redenvelope.ui.activity.ad.CreateAdContentActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/2.
 */
public class CreateAdContentModeImpl implements CreateAdContentMode {

    private CreateAdContentActivity mActivity;
    private HttpManager mManager;

    public CreateAdContentModeImpl(CreateAdContentActivity createAdNextPageActivity) {
        this.mActivity = createAdNextPageActivity;
        mManager = new HttpManager();
    }

    @Override
    public void commitAdCreate(final String token, final AdEntity adEntity, final String title, final String content, final BaseMultiLoadedListener listener) {
        mManager.commitAdCreate(token, adEntity, title, content, listener, mActivity);
    }
}
