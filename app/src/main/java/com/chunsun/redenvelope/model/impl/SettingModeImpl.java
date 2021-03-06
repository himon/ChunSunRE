package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.SettingMode;
import com.chunsun.redenvelope.ui.activity.personal.SettingActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/24.
 */
public class SettingModeImpl implements SettingMode {

    private SettingActivity mActivity;
    private HttpManager mManager;

    public SettingModeImpl(SettingActivity mSettingActivity) {
        this.mActivity = mSettingActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void logout(final String token, final UserLoseMultiLoadedListener listener) {
        mManager.logout(token, listener, mActivity);
    }

    @Override
    public void upGrade(UserLoseMultiLoadedListener listener) {
        mManager.upGrade(listener, mActivity);
    }
}
