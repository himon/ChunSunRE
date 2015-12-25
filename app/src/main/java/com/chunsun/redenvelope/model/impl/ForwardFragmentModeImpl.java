package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.ForwardFragmentMode;
import com.chunsun.redenvelope.ui.fragment.tab.ForwardFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/5.
 */
public class ForwardFragmentModeImpl implements ForwardFragmentMode {

    private ForwardFragment mForwardFragment;
    private HttpManager mManager;

    public ForwardFragmentModeImpl(ForwardFragment homeFragment) {
        this.mForwardFragment = homeFragment;
        this.mManager = new HttpManager();
    }

    @Override
    public void loadData(final String token, final String type, final int page_index, final UserLoseMultiLoadedListener listener) {
        mManager.loadData(token, type, page_index, listener, mForwardFragment, null);
    }

    @Override
    public void getAdData(final String type, final BaseMultiLoadedListener listener) {
        mManager.getAdData(type, listener, mForwardFragment, null);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.grabRedEnvelope(token, hb_id, listener, mForwardFragment, null);
    }
}
