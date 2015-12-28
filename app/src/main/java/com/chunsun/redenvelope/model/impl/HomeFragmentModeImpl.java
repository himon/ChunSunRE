package com.chunsun.redenvelope.model.impl;

import android.app.Activity;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.listeners.UserLoseMultiLoadedListener;
import com.chunsun.redenvelope.model.HomeFragmentMode;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/10.
 */
public class HomeFragmentModeImpl implements HomeFragmentMode {
    private HomeFragment mFragment;
    private Activity mActivity;
    private HttpManager mManager;

    public HomeFragmentModeImpl(HomeFragment fragment, Activity activity) {
        this.mFragment = fragment;
        this.mActivity = activity;
        mManager = new HttpManager();
    }

    /**
     * 获取列表数据
     *
     * @param token
     * @param type       -1（生活、企业），3（附近）
     * @param page_index
     * @param listener
     */
    @Override
    public void loadData(final String token, final int type, final int page_index, int order_type, String keywords, final UserLoseMultiLoadedListener listener) {
        mManager.loadData(token, type, page_index, order_type, keywords, listener, mFragment, mActivity);
    }

    @Override
    public void getAdData(final String type, final BaseMultiLoadedListener listener) {
        mManager.getAdData(type, listener, mFragment, mActivity);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final UserLoseMultiLoadedListener listener) {
        mManager.grabRedEnvelope(token, hb_id, listener, mFragment, null);
    }
}
