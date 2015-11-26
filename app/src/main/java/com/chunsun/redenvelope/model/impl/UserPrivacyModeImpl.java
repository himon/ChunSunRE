package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseSingleLoadedListener;
import com.chunsun.redenvelope.model.UserPrivacyMode;
import com.chunsun.redenvelope.ui.activity.personal.UserPrivacyActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/24.
 */
public class UserPrivacyModeImpl implements UserPrivacyMode {

    private UserPrivacyActivity mActivity;
    private HttpManager mManager;

    public UserPrivacyModeImpl(UserPrivacyActivity userPrivacyActivity) {
        this.mActivity = userPrivacyActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void updateUserPrivacySetting(final String token, final String field_value, final BaseSingleLoadedListener listener) {
        mManager.updateUserPrivacySetting(token, field_value, listener, mActivity);
    }
}
