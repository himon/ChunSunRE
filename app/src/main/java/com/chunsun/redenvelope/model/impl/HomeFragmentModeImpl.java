package com.chunsun.redenvelope.model.impl;

import com.chunsun.redenvelope.listeners.BaseMultiLoadedListener;
import com.chunsun.redenvelope.model.HomeFragmentMode;
import com.chunsun.redenvelope.ui.fragment.tab.HomeFragment;
import com.chunsun.redenvelope.utils.manager.HttpManager;

/**
 * Created by Administrator on 2015/8/10.
 */
public class HomeFragmentModeImpl implements HomeFragmentMode {
    private HomeFragment mHomeFragment;
    private HttpManager mManager;

    public HomeFragmentModeImpl(HomeFragment nearFragment) {
        this.mHomeFragment = nearFragment;
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
    public void loadData(final String token, final String type, final int page_index, final BaseMultiLoadedListener listener) {
        mManager.loadData(token, type, page_index, listener, mHomeFragment);
    }

    @Override
    public void getAdData(final String type, final BaseMultiLoadedListener listener) {
        mManager.getAdData(type, listener, mHomeFragment);
    }

    @Override
    public void grabRedEnvelope(final String token, final String hb_id, final BaseMultiLoadedListener listener) {
        mManager.grabRedEnvelope(token, hb_id, listener, mHomeFragment, null);
    }
}
