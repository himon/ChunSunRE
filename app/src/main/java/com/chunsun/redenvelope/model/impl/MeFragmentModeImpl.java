package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.MeFragmentMode;
import com.chunsun.redenvelope.ui.fragment.tab.NewMeFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/3.
 */
public class MeFragmentModeImpl implements MeFragmentMode {

    private NewMeFragment meFragment;
    private HttpManager mManager;

    public MeFragmentModeImpl(NewMeFragment meFragment) {
        this.meFragment = meFragment;
        this.mManager = new HttpManager();
    }

    @Override
    public void getUserInfomation(final String token, final BaseMultiLoadedListener listener) {
        mManager.getUserInfomation(token, listener, meFragment, null);
    }
}
