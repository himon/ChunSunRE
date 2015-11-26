package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MineInviteCodeMode;
import com.chunsun.redenvelope.ui.activity.personal.MineInviteCodeWebActivity;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/22.
 */
public class MineInviteCodeModeImpl implements MineInviteCodeMode {

    private MineInviteCodeWebActivity mActivity;
    private HttpManager mManager;

    public MineInviteCodeModeImpl(MineInviteCodeWebActivity mineInviteCodeActivity) {
        this.mActivity = mineInviteCodeActivity;
        this.mManager = new HttpManager();
    }

    @Override
    public void getInviteRecord(final String token, final BaseMultiLoadedListener listener) {
        mManager.getInviteRecord(token, listener, mActivity);
    }

    @Override
    public void getUserInfomation(final String token, final BaseMultiLoadedListener listener) {
        mManager.getUserInfomation(token, listener, null, mActivity);
    }
}
