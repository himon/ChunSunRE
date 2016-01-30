package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.ui.base.mode.BaseModeImpl;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentModeImpl extends BaseModeImpl implements MeFragmentMode {

    private NewMeFragment meFragment;

    public MeFragmentModeImpl(NewMeFragment meFragment) {
        this.meFragment = meFragment;
    }

    @Override
    public void getUserInfomation(final String token, final UserLoseMultiLoadedListener listener) {
        mManager.getUserInfomation(token, listener, meFragment, null);
    }
}
