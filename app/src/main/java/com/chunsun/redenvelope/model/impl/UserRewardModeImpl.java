package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.impl.BaseMultiLoadedListenerImpl;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.UserRewardMode;
import com.chunsun.redenvelope.ui.activity.red.UserRewardActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/9/15.
 */
public class UserRewardModeImpl implements UserRewardMode {

    private UserRewardActivity mActivity;
    private HttpManager mManager;

    public UserRewardModeImpl(UserRewardActivity userRewardActivity) {
        mActivity = userRewardActivity;
        mManager = new HttpManager();
    }

    @Override
    public void getUserPublicData(final String token, final String user_id, final UserLoseMultiLoadedListener listener) {
        mManager.getUserPublicData(token, user_id, listener, mActivity);
    }

    @Override
    public void getUserAmount(final String token, final BaseMultiLoadedListenerImpl listener) {
        mManager.loadWalletData(token, listener, mActivity);
    }

    @Override
    public void transfer(final String token, final String user_id, final String amount, final String msg, final String hb_id, final String province, final String city, final UserLoseMultiLoadedListener listener) {
        mManager.transfer(token, user_id, amount, msg, hb_id, province, city, listener, mActivity);
    }
}
