package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.UserInfoMode;
import com.chunsun.redenvelope.ui.activity.personal.UserInfoActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/15.
 */
public class UserInfoModeImpl implements UserInfoMode {

    private UserInfoActivity mActivity;
    private HttpManager mManager;

    public UserInfoModeImpl(UserInfoActivity activity) {
        mActivity = activity;
        mManager = new HttpManager();
    }

    @Override
    public void editUserInfo(final String token, final String field_name, final String field_value, final UserLoseMultiLoadedListener listener) {
        mManager.editUserInfo(token, field_name, field_value, listener, mActivity);
    }
}
